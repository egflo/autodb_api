package com.autodb_api.services;


import com.autodb_api.dto.AutoDTO;
import com.autodb_api.models.*;
import com.autodb_api.repositories.*;
import com.autodb_api.utilities.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import dao.AutoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutoService {

    @Autowired
    private API api;

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private ColorRepository colorService;

    @Autowired
    private TransmissionRepository transmissionRepository;

    @Autowired
    private DrivetrainRepository drivetrainRepository;

    @Autowired
    private BodyTypeRepository bodyTypeRepository;

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private LocationRepository locationRepository;

    @PersistenceContext
    EntityManager entityManager;


    public ArrayList<Transmission> getAllTransmissions() {
        return transmissionRepository.findAll();
    }

    public ArrayList<Color> getAllColors() {
        return colorService.findAll();
    }

    public ArrayList<Fuel> getAllFuels() {
        return fuelRepository.findAll();
    }

    public ArrayList<Drivetrain> getAllDrivetrains() {
        return drivetrainRepository.findAll();
    }

    public Optional<Auto> getAuto(Integer id) {
        Optional<Auto> data = autoRepository.findById(id);
        if (data.isPresent()) {
            Auto auto = data.get();

            AutoDTO autoDTO = new AutoDTO();
            autoDTO.setId(auto.getId());
            autoDTO.setMake(auto.getMake().getName());
            autoDTO.setModel(auto.getModel());
            autoDTO.setYear(auto.getYear());
            autoDTO.setColor(auto.getColor().getName());

            api.getSafetyRatingsForVehicle(autoDTO);
        }
        return data;
    }

    public ArrayList<String> getAllModelsByMake(String make) {
        ArrayList<String> models = autoRepository.findModelsByMake(make);
        return models;
    }

    public Page<Auto> getAutoByMakeName(String name, PageRequest pageRequest) {
        return autoRepository.findByMakeName(name, pageRequest);
    }

    public Page<Auto> getAutoByYear(Integer year, PageRequest pageRequest) {
        return autoRepository.findByYear(year, pageRequest);
    }

    public Page<Auto> getAutoByModel(String model, PageRequest pageRequest) {
        return autoRepository.findAutoByModelNameLike(model, pageRequest);
    }

    public Page<Auto> getAutoByMakeNameAndYear(String name, Integer year, PageRequest pageRequest) {
        return autoRepository.findByMakeNameAndYear(name, year, pageRequest);
    }

    public Page<Auto> getAutoByMakeNameAndYearAndModel(String name, Integer year, String model, PageRequest pageRequest) {
        return autoRepository.findByMakeNameAndYearAndModelName(name, year, model, pageRequest);
    }

    public Page<Auto> findAll(Pageable pageable) {
        return autoRepository.findAll(pageable);
    }


    public Page<Auto> search(ArrayList<String> queries,
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
                             Optional<Double> price_min,
                             Optional<Double> price_max,
                             Optional<String> condition_code,
                             Pageable pageable) {

        AutoDao autoDao = new AutoDao(entityManager, bodyTypeRepository, locationRepository);
        return autoDao.findAutoByParams(queries, color_code, body_code, drivetrain_code,
                fuel_code, transmission_code, start_year, end_year, mileage, postcode, radius,
                price_min, price_max, condition_code, pageable);
    }

    public Object getAutoByPostcode(String postcode, Integer miles) {
        try {
            //https://stackoverflow.com/questions/62366229/work-out-the-50-miles-radius-from-london-latitude-and-longitude-coordinates
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyBWfq77pnFwWGahK9nzde6cTxsRfaOnK0M")
                    .build();
            GeocodingResult[] results =  GeocodingApi.geocode(context,
                    String.valueOf(postcode)).await();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Geometry geometry = results[0].geometry;
            List<Dealer> dealers = dealerRepository.findNearbyDealers(geometry.location.lat, geometry.location.lng, Double.valueOf(miles));
            //System.out.println(gson.toJson(results[0].geometry));
            //System.out.println(gson.toJson(dealers));
            context.shutdown();

            return dealers;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
