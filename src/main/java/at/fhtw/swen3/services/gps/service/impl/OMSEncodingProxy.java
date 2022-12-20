package at.fhtw.swen3.services.gps.service.impl;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.gps.service.GeoEncodingCallService;
import at.fhtw.swen3.services.gps.service.GeoEncodingService;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

@Slf4j
public class OMSEncodingProxy implements GeoEncodingService {

    private final GeoEncodingCallService service;

    public OMSEncodingProxy() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nominatim.openstreetmap.org/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        service = retrofit.create(GeoEncodingCallService.class);
    }

    @Override
    public GeoCoordinateEntity encodeAddress(String address) {
        try{
            log.info("Retrieving GeoCoordinate Information from OMS");
            var result = service.getGeoEncodingInfo(1, address, "json", 1).execute().body();
            return result.get(0);
        } catch (IOException e) {
            log.error("Get Weather for tour [address: "+address+"] failed [error: "+e.getMessage()+"]");
        }
        return null;
    }
}
