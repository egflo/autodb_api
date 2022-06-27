package com.autodb_api.services;


import com.autodb_api.dto.AutoDTO;
import com.autodb_api.models.*;
import com.autodb_api.repositories.*;
import com.autodb_api.utilities.API;
import dao.AutoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
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
            autoDTO.setModel(auto.getModelName());
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


    public Page<Auto> search(ArrayList<String> queries, Optional<String> color_code, Optional<String> body_code, Optional<String> drivetrain_code, Optional<String> fuel_code, Optional<String> transmission_code, Pageable pageable) {

        AutoDao autoDao = new AutoDao(entityManager);
        return autoDao.findAutoByParams(queries, color_code, body_code, drivetrain_code, fuel_code, transmission_code, pageable);
    }
}
