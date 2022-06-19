package com.autodb_api.services;


import com.autodb_api.dto.AutoDTO;
import com.autodb_api.models.Auto;
import com.autodb_api.repositories.AutoRepository;
import com.autodb_api.utilities.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutoService {

    @Autowired
    private API api;

    @Autowired
    private AutoRepository autoRepository;


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
}
