package com.autodb_api.services;


import com.autodb_api.models.Body;
import com.autodb_api.models.BodyType;
import com.autodb_api.models.Dealer;
import com.autodb_api.repositories.BodyRepository;
import com.autodb_api.repositories.BodyTypeRepository;
import com.autodb_api.repositories.DealerRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DealerService {

    @Value("${googleApiKey}")
    private String googleApiKey;

    @Autowired
    private DealerRepository repository;

    public Optional<Dealer> findById(Integer id) {
        return repository.findById(id);
    }


    public List<Dealer> findAllByPostCode (Integer postcode, Integer distance) {
        try {
            //https://stackoverflow.com/questions/62366229/work-out-the-50-miles-radius-from-london-latitude-and-longitude-coordinates
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(googleApiKey)
                    .build();
            GeocodingResult[] results =  GeocodingApi.geocode(context,
                    String.valueOf(postcode)).await();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Geometry geometry = results[0].geometry;
            Double lat = geometry.location.lat;
            Double lng = geometry.location.lng;
            List<Dealer> dealers = repository.findAllSellersByPostcodeAndRadius(lat, lng, distance);
            //List<Dealer> dealers = repository.findNearbySellers(geometry.location.lng, geometry.location.lat, distance);
            context.shutdown();
            return dealers;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null ;
    }


    public Page<Dealer> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }
}
