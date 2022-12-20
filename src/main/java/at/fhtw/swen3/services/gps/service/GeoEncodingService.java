package at.fhtw.swen3.services.gps.service;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;

public interface GeoEncodingService {
    GeoCoordinateEntity encodeAddress(String address);
}
