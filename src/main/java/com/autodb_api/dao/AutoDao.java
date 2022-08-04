package com.autodb_api.dao;

import com.autodb_api.models.*;
import com.autodb_api.repositories.BodyTypeRepository;
import com.autodb_api.repositories.LocationRepository;
import com.autodb_api.repositories.MakeRepository;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import org.hibernate.spatial.predicate.SpatialPredicates;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.*;


@Repository("AutoDao")
public
class AutoDao {
    @Value("${googleApiKey}")
    private String googleApiKey;
    BodyTypeRepository bodyTypeRepository;

    MakeRepository makeRepository;

    LocationRepository locationRepository;

    EntityManager entityManager;

    // WGS-84 SRID
    private GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);

    public AutoDao(EntityManager entityManager,
                   BodyTypeRepository bodyTypeRepository,
                   MakeRepository makeRepository,
                   LocationRepository locationRepository) {
        this.entityManager = entityManager;
        this.bodyTypeRepository = bodyTypeRepository;
        this.locationRepository = locationRepository;
        this.makeRepository = makeRepository;

    }

    private List<String> getBodyTypes() {
        List<BodyType> bodyTypes = bodyTypeRepository.findAll();
        List<String> bodyTypeNames = new ArrayList<>();
        for (BodyType bodyType : bodyTypes) {
            bodyTypeNames.add(bodyType.getType().toLowerCase());
        }
        return bodyTypeNames;
    }

    private List<String> getMakes() {
        List<Make> makes = makeRepository.findAll();
        List<String> makeNames = new ArrayList<>();
        for (Make make : makes) {
            makeNames.add(make.getName().toLowerCase());
        }
        return makeNames;
    }

    private Long getTotalCount(CriteriaBuilder criteriaBuilder, Predicate queryPredicate) {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        //Root<Auto> root = criteriaQuery.from(Auto.class);
        //criteriaQuery.select(criteriaBuilder.count(root));
        ///criteriaQuery.where(predicateArray);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Auto.class)));
        criteriaQuery.where(queryPredicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public boolean isColor(String color) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Color> criteriaQuery = criteriaBuilder.createQuery(Color.class);
        Root<Color> root = criteriaQuery.from(Color.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(criteriaBuilder.lower(root.get("name")), color.toLowerCase()));

        return entityManager.createQuery(criteriaQuery).getResultList().size() > 0;

    }
    public Double milesToMeters(Optional<Integer> miles) {
        if (miles.isPresent()) {
            return miles.get() * 1609.34;
        }
        return 250 * 1609.34;
    }

    public AbstractMap.SimpleEntry<List<String>, List<String>> parseParams(ArrayList<String>params) {
        List<String> bodyTypes = getBodyTypes();
        List<String> makes = getMakes();

        List<String> type_params = new ArrayList<>();
        List<String> makes_params = new ArrayList<>();

        for (String param : params) {
           if(bodyTypes.contains(param.toLowerCase())) {
               type_params.add(param.toLowerCase());
           } else if(makes.contains(param.toLowerCase())) {
                makes_params.add(param.toLowerCase());
           }
           else {
               makes_params.add("all");
           }
        }

        AbstractMap.SimpleEntry<List<String>, List<String>> params_map =
                new AbstractMap.SimpleEntry<>(makes_params, type_params);

        return params_map;
    }

    public Page<Auto> findAutoByParams(ArrayList<String> params,
                                       Optional<String> color_code,
                                       Optional<String> body_code,
                                       Optional<String> drivetrain_code,
                                       Optional<String> fuel_code,
                                       Optional<String> transmission_code,
                                       Optional<Integer> start_year,
                                       Optional<Integer> end_year,
                                       Optional<Double> mileage,
                                       Optional<Integer> postcode,
                                       Optional<Integer> radius,
                                       Optional<Double> priceMin,
                                       Optional<Double> priceMax,
                                       Optional<String> condition_code,
                                       Optional<String> model_code,
                                       Pageable pageRequest) {


        List<String> bodyTypes = getBodyTypes();
        //Get CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //Create CriteriaQuery
        CriteriaQuery<Auto> criteriaQuery = criteriaBuilder.createQuery(Auto.class);
        //Create Root
        Root<Auto> root = criteriaQuery.from(Auto.class);

        List<Predicate> predicates = new ArrayList<>();

        AbstractMap.SimpleEntry<List<String>, List<String>> tuple
                = parseParams(params);


        List<String> makes = tuple.getKey();
        List<String> types = tuple.getValue();

        for(String param :makes.size() > 0 ? makes : getMakes()) {
            List<Predicate> subPredicates = new ArrayList<>();

            Predicate makePredicate = criteriaBuilder.equal(criteriaBuilder.lower(root.get("make").get("name")), param.toLowerCase());
            subPredicates.add(makePredicate);

            if(types.size() > 0) {
                List<Predicate> typePredicates = new ArrayList<>();
                for(String type : types) {
                    Predicate typePredicate = criteriaBuilder.equal(criteriaBuilder.lower(root.get("body").get("bodyType").get("type")), type.toLowerCase());
                    typePredicates.add(typePredicate);
                }
                subPredicates.add(criteriaBuilder.or(typePredicates.toArray(new Predicate[typePredicates.size()])));
            }

            if(model_code.isPresent()) {
                String[] codes = model_code.get().split("_");
                List<Predicate> modelPredicates = new ArrayList<>();

                for(String code: codes) {
                    Predicate predicate =  criteriaBuilder.equal(root.get("model").get("id"), Integer.parseInt(code));
                    modelPredicates.add(predicate);
                }

                Predicate modelPredicate = criteriaBuilder.or(modelPredicates.toArray(new Predicate[modelPredicates.size()]));
                subPredicates.add(modelPredicate);
            }


            if(condition_code.isPresent()) {
                String[] codes = condition_code.get().split("_");
                List<Predicate> conditionPredicates = new ArrayList<>();

                for(String code : codes) {
                    if(code.equals("isNew")) {
                        conditionPredicates.add(criteriaBuilder.equal(root.get("isNew"), true));
                    }
                    else if (code.equals("isOemcpo")) {
                        conditionPredicates.add(criteriaBuilder.equal(root.get("isOemcpo"), true));
                    }
                    else if (code.equals("isCpo")) {
                        conditionPredicates.add(criteriaBuilder.equal(root.get("isCpo"), true));
                    }
                    else if(code.equals("isUsed")) {
                        //Look for used condition inverse of is_new
                        conditionPredicates.add(criteriaBuilder.equal(root.get("isNew"), false));
                    }

                }
                subPredicates.add(criteriaBuilder.and(conditionPredicates.toArray(new Predicate[conditionPredicates.size()])));
            }

            if(color_code.isPresent()) {
                String[] codes = color_code.get().split("_");
                List<Predicate> drivetrainPredicates = new ArrayList<>();

                for(String code: codes) {
                    Predicate colorPredicate =  criteriaBuilder.equal(root.get("color").get("id"), Integer.parseInt(code));
                    drivetrainPredicates.add(colorPredicate);
                }

                Predicate drivetrainPredicate = criteriaBuilder.or(drivetrainPredicates.toArray(new Predicate[drivetrainPredicates.size()]));
                subPredicates.add(drivetrainPredicate);
            }

            if(body_code.isPresent()) {
                String[] codes = body_code.get().split("_");
                List<Predicate> drivetrainPredicates = new ArrayList<>();
                for(String code: codes) {
                    Predicate bodyPredicate =  criteriaBuilder.equal(root.get("body").get("bodyType").get("id"), Integer.parseInt(code));
                    drivetrainPredicates.add(bodyPredicate);
                }

                Predicate drivetrainPredicate = criteriaBuilder.or(drivetrainPredicates.toArray(new Predicate[drivetrainPredicates.size()]));
                subPredicates.add(drivetrainPredicate);
            }

            if(drivetrain_code.isPresent()) {
                String[] codes = drivetrain_code.get().split("_");
                List<Predicate> drivetrainPredicates = new ArrayList<>();
                for(String code: codes) {
                    Predicate drivetrainPredicate =  criteriaBuilder.equal(root.get("drivetrain").get("id"), Integer.parseInt(code));
                    drivetrainPredicates.add(drivetrainPredicate);
                }
                Predicate drivetrainPredicate = criteriaBuilder.or(drivetrainPredicates.toArray(new Predicate[drivetrainPredicates.size()]));
                subPredicates.add(drivetrainPredicate);
            }

            if(fuel_code.isPresent()) {
                String[] codes = fuel_code.get().split("_");
                List<Predicate> fuelPredicates = new ArrayList<>();
                for(String code: codes) {
                    Predicate fuelPredicate =  criteriaBuilder.equal(root.get("fuel").get("id"), Integer.parseInt(code));
                    fuelPredicates.add(fuelPredicate);
                }
                Predicate fuelPredicate = criteriaBuilder.or(fuelPredicates.toArray(new Predicate[fuelPredicates.size()]));
                subPredicates.add(fuelPredicate);
            }

            if(transmission_code.isPresent()) {
                String[] codes = transmission_code.get().split("_");
                List<Predicate> transmissionPredicates = new ArrayList<>();
                for(String code: codes) {
                    Predicate transmissionPredicate =  criteriaBuilder.equal(root.get("transmission").get("type"), code);
                    transmissionPredicates.add(transmissionPredicate);
                }

                Predicate transmissionPredicate = criteriaBuilder.or(transmissionPredicates.toArray(new Predicate[transmissionPredicates.size()]));
                subPredicates.add(transmissionPredicate);

            }

            if(start_year.isPresent() && end_year.isPresent()) {
                Predicate yearPredicate =  criteriaBuilder.between(root.get("year"), start_year.get(), end_year.get());
                subPredicates.add(yearPredicate);
            } else if (start_year.isPresent()) {
                Predicate yearPredicate =  criteriaBuilder.greaterThanOrEqualTo(root.get("year"), start_year.get());
                subPredicates.add(yearPredicate);
            } else if (end_year.isPresent()) {
                Predicate yearPredicate =  criteriaBuilder.lessThanOrEqualTo(root.get("year"), end_year.get());
                subPredicates.add(yearPredicate);
            }

            if(mileage.isPresent()) {
                Predicate mileagePredicate =  criteriaBuilder.lessThanOrEqualTo(root.get("mileage"), mileage.get());
                subPredicates.add(mileagePredicate);
            }

            if(postcode.isPresent()) {
                try {
                    Optional<Location> location = locationRepository.findByPostcode(postcode.get());
                    if(location.isPresent()) {
                        Predicate dealerPredicate = SpatialPredicates.distanceWithin(criteriaBuilder, root.get("dealer").get("location"), location.get().getPoint(), milesToMeters(radius));
                        subPredicates.add(dealerPredicate);
                    } else {
                        GeoApiContext context = new GeoApiContext.Builder()
                                .apiKey(googleApiKey)
                                .build();
                        GeocodingResult[] results =  GeocodingApi.geocode(context,
                                String.valueOf(postcode)).await();
                        Geometry geometry = results[0].geometry;

                        Location newLocation = new Location();
                        newLocation.setPostcode(postcode.get());
                        Coordinate coordinate = new Coordinate(geometry.location.lng, geometry.location.lat);
                        Point comparisonPoint = factory.createPoint(coordinate);
                        newLocation.setPoint(comparisonPoint);

                        Location save = locationRepository.save(newLocation);

                        Predicate dealerPredicate = SpatialPredicates.distanceWithin(criteriaBuilder, root.get("dealer").get("location"), save.getPoint(), milesToMeters(radius));
                        subPredicates.add(dealerPredicate);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if(priceMin.isPresent() && priceMax.isPresent()) {
                Predicate pricePredicate =  criteriaBuilder.between(root.get("price"), priceMin.get(), priceMax.get());
                subPredicates.add(pricePredicate);
            } else if (priceMin.isPresent()) {
                Predicate pricePredicate =  criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceMin.get());
                subPredicates.add(pricePredicate);
            } else if (priceMax.isPresent()) {
                Predicate pricePredicate =  criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceMax.get());
                subPredicates.add(pricePredicate);
            }

            predicates.add(criteriaBuilder.and(subPredicates.toArray(new Predicate[subPredicates.size()])));

        }

        Predicate finalQuery = criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));



        Sort sort = pageRequest.getSort();

        for (Sort.Order order : sort)
        {
            String property = order.getProperty();
            String direction = order.getDirection().name();
            System.out.println("Property: " + order.getProperty());
            System.out.println("Direction: " + order.getDirection());

            if(property.toLowerCase().contains("optional")){
                continue;
            }

            if(direction.equals("ASC")) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(property)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(property)));
            }
        }



        List<Auto> result =
                entityManager.createQuery(criteriaQuery.select(root).where(finalQuery))
                        .setFirstResult((int) pageRequest.getOffset())
                        .setMaxResults(pageRequest.getPageSize())
                        .getResultList();



        Page<Auto> page = new PageImpl<>(result, pageRequest, getTotalCount(criteriaBuilder, finalQuery));
        return page;
    }
}
