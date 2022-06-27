package com.autodb_api.utilities;

import com.autodb_api.dto.AutoDTO;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Optional;

import com.autodb_api.models.Image;
import com.autodb_api.models.Safety;
import com.autodb_api.repositories.ImageRepository;
import com.autodb_api.repositories.SafetyRepository;
import com.autodb_api.response.NHSTAVehicle;
import com.google.gson.*;
import org.apache.commons.httpclient.URI;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class API {
    public static final String API_VERSION = "v1";

    @Autowired
    private SafetyRepository safetyRepository;

    @Autowired
    private ImageRepository imageRepository;



    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, "UTF-8").replace("+", "%20");
    }

    public int getSafetyRatingsForVehicle(AutoDTO auto) {
        try {

            //Create URL encoded string
            if(true) {
                return 0;
            }

            //Create URL encoded string
            String urlEncoded = "https://api.nhtsa.gov/SafetyRatings/modelyear/"+
                    auto.getYear() + "/make/" + auto.getMake() + "/model/" + auto.getModel();

            // Create a neat value object to hold the URL
            URI uri = new URI(urlEncoded, null);

            URL url = new URL(uri.toString());
            System.out.println(uri.toString());

            // Open a connection(?) on the URL(??) and cast the response(???)
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Now it's "open", we can set the request method, headers etc.
            conn.setRequestProperty("accept", "application/json");

            // This line makes the request
            InputStream inputStream = conn.getInputStream();

            //Convert to String JSON
            String content = IOUtils.toString(inputStream);


            // Parse the JSON
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(content);
            JsonObject obj = element.getAsJsonObject();
            JsonArray arr = obj.getAsJsonArray("Results");

            if(arr.size() != 0) {
                JsonObject object = arr.get(0).getAsJsonObject();
                Integer vehicle_id = object.get("VehicleId").getAsInt();
                return getSafetyRatingsForVehicleId(vehicle_id, auto);
            }

            else {
                return 0;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
        }
        return 0;
    }


    public int getSafetyRatingsForVehicleId(Integer vehicle_id, AutoDTO auto) {
        try {

            // Create a neat value object to hold the URL
            URL url = new URL("https://api.nhtsa.gov/SafetyRatings/VehicleId/"+ vehicle_id);

            // Open a connection(?) on the URL(??) and cast the response(???)
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Now it's "open", we can set the request method, headers etc.
            conn.setRequestProperty("accept", "application/json");

            // This line makes the request
            InputStream inputStream = conn.getInputStream();

            //Convert to String JSON
            String content = IOUtils.toString(inputStream);


            // Parse the JSON
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(content);
            JsonObject obj = element.getAsJsonObject();
            JsonArray arr = obj.getAsJsonArray("Results");

            if(arr.size() != 0) {

                Gson gson = new Gson();

                for(int i = 0; i < arr.size(); i++) {
                    JsonObject object = arr.get(i).getAsJsonObject();
                    NHSTAVehicle vehicle = gson.fromJson(object, NHSTAVehicle.class);
                    System.out.println(vehicle.vehicleId);


                    //imageRepository.findByUrl(vehicle.vehicleId).ifPresent(image -> {
                    //    image.setSafety(safetyRepository.findByRating(vehicle.safetyRating).get());
                    //    imageRepository.save(image);
                    //});

                    if(!imageRepository.findByUrl(vehicle.vehiclePicture).isPresent()) {
  ;                    Image image = new Image();
                        image.setUrl(vehicle.vehiclePicture);
                        image.setAutoId(auto.getId());

                        Image Image = imageRepository.save(image);
                    }


                    Optional<Safety> found = safetyRepository.findByVehicleId(vehicle.vehicleId);

                    if(!found.isPresent()) {
                        Safety safety = new Safety();
                        safety.setVehicleId(vehicle.vehicleId);
                        safety.setMake(vehicle.make);
                        safety.setModel(vehicle.model);
                        safety.setModelYear(vehicle.modelYear);
                        safety.setVehicleDescription(vehicle.vehicleDescription);

                        safety.setVehiclePicture(vehicle.vehiclePicture);
                        safety.setOverallRating(vehicle.overallRating);
                        safety.setFrontCrashDriversideRating(vehicle.frontCrashDriversideRating);
                        safety.setFrontCrashPassengersideRating(vehicle.frontCrashPassengersideRating);
                        safety.setFrontCrashPicture(vehicle.frontCrashPicture);
                        safety.setFrontCrashVideo(vehicle.frontCrashVideo);

                        safety.setOverallSideCrashRating(vehicle.overallSideCrashRating);
                        safety.setSideCrashDriversideRating(vehicle.sideCrashDriversideRating);
                        safety.setSideCrashPassengersideRating(vehicle.sideCrashPassengersideRating);
                        safety.setSideCrashPicture(vehicle.sideCrashPicture);
                        safety.setSideCrashVideo(vehicle.sideCrashVideo);

                        safety.setCombinedSideBarrierAndPoleRatingFront(vehicle.combinedSideBarrierAndPoleRatingFront);
                        safety.setCombinedSideBarrierAndPoleRatingRear(vehicle.combinedSideBarrierAndPoleRatingRear);
                        safety.setSideBarrierRatingOverall(vehicle.sideBarrierRatingOverall);

                        safety.setRolloverRating(vehicle.rolloverRating);
                        safety.setRolloverRating2(vehicle.rolloverRating2);
                        safety.setRolloverPossibility(vehicle.rolloverPossibility);
                        safety.setRolloverPossibility2(vehicle.rolloverPossibility2);

                        safety.setDynamicTipResult(vehicle.dynamicTipResult);
                        safety.setSidePoleCrashRating(vehicle.sidePoleCrashRating);

                        safety.setNHTSAElectronicStabilityControl(vehicle.nHTSAElectronicStabilityControl);
                        safety.setNHTSAForwardCollisionWarning(vehicle.nHTSAForwardCollisionWarning);
                        safety.setNHTSALaneDepartureWarning(vehicle.nHTSAForwardCollisionWarning);

                        safety.setComplaintsCount(vehicle.complaintsCount);
                        safety.setRecallsCount(vehicle.recallsCount);
                        safety.setInvestigationCount(vehicle.investigationCount);

                        safetyRepository.save(safety);
                    }


                }

                return vehicle_id;
            }

            else {
                return 0;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
        }
        return 0;
    }


    void processSafetyRatings(Safety safety) {
        try {
            // Create a neat value object to hold the URL
            safetyRepository.save(safety);
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
        }
    }

}
