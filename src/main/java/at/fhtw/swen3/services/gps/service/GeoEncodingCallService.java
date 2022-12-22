package at.fhtw.swen3.services.gps.service;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface GeoEncodingCallService {

    @GET(".")
    Call<List<GeoCoordinateEntity>> getGeoEncodingInfo(
            @Query("addressdetails") int details,
            @Query("q") String query,
            @Query("format") String format,
            @Query("limit") int limit
    );
}
