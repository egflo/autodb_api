package dao;

import com.autodb_api.models.Auto;
import com.autodb_api.models.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository("AutoDao")
public
class AutoDao {
    EntityManager entityManager;

    public AutoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    private Long getTotalCount(CriteriaBuilder criteriaBuilder, Predicate[] predicateArray) {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Auto> root = criteriaQuery.from(Auto.class);
        criteriaQuery.select(criteriaBuilder.count(root));
        criteriaQuery.where(predicateArray);

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

    public Page<Auto> findAutoByParams(ArrayList<String> params, Optional<String> color_code, Optional<String> body_code, Optional<String> drivetrain_code, Optional<String> fuel_code, Optional<String> transmission_code, Pageable pageRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Auto> criteriaQuery = criteriaBuilder.createQuery(Auto.class);
        Root<Auto> root = criteriaQuery.from(Auto.class);

        List<Predicate> predicates = new ArrayList<>();
        for(String param : params) {

            List<Predicate> subPredicates = new ArrayList<>();

            Predicate makePredicate = criteriaBuilder.equal(criteriaBuilder.lower(root.get("make").get("name")), param.toLowerCase());

            if(color_code.isPresent()) {
                String[] codes = color_code.get().split("_");
                for(String code: codes) {
                    Predicate colorPredicate =  criteriaBuilder.equal(root.get("color").get("id"), Integer.parseInt(code));
                    //Predicate finalPredicate = criteriaBuilder.and(makePredicate, colorPredicate);
                    //predicates.add(finalPredicate);
                    subPredicates.add(colorPredicate);
                }
            }

            if(body_code.isPresent()) {
                String[] codes = body_code.get().split("_");
                for(String code: codes) {
                    Predicate bodyPredicate =  criteriaBuilder.equal(root.get("body").get("bodyType").get("id"), Integer.parseInt(code));
                    subPredicates.add(bodyPredicate);
                }
            }

            if(drivetrain_code.isPresent()) {
                String[] codes = drivetrain_code.get().split("_");
                for(String code: codes) {
                    Predicate drivetrainPredicate =  criteriaBuilder.equal(root.get("drivetrain").get("id"), Integer.parseInt(code));
                    subPredicates.add(drivetrainPredicate);
                }
            }

            if(fuel_code.isPresent()) {
                String[] codes = fuel_code.get().split("_");
                for(String code: codes) {
                    Predicate fuelPredicate =  criteriaBuilder.equal(root.get("fuel").get("id"), Integer.parseInt(code));
                    subPredicates.add(fuelPredicate);
                }
            }

            subPredicates.add(makePredicate);

            //(color and body and make) or (color and make) or (body and make) or (make)
            predicates.add(criteriaBuilder.and(subPredicates.toArray(new Predicate[subPredicates.size()])));

        }


        Predicate finalQuery = criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));

        List<Auto> result =
                entityManager.createQuery(criteriaQuery.select(root).where(finalQuery))
                        .setFirstResult((int) pageRequest.getOffset())
                        .setMaxResults(pageRequest.getPageSize())
                        .getResultList();

        //get total count of results

        //Long count = Long.valueOf(result.size());
        //Long count = Long.valueOf(entityManager.createQuery(criteriaQuery.select(root).where(finalQuery)).getResultList().size());

        Page<Auto> page = new PageImpl<>(result, pageRequest, getTotalCount(criteriaBuilder, predicates.toArray(new Predicate[predicates.size()])));
        return page;
    }
}
