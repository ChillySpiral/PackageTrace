package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.exceptions.BLDataNotFoundException;
import at.fhtw.swen3.services.exceptions.BLValidationException;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ParcelService {

    Optional<ParcelEntity> submitParcel(ParcelEntity parcel) throws BLValidationException;

    Optional<ParcelEntity> trackParcel(String trackingId) throws BLDataNotFoundException;

    boolean reportParcelDelivery(String trackingId) throws BLDataNotFoundException;

    boolean reportParcelHop(String trackingId, String code) throws BLDataNotFoundException;

    Optional<ParcelEntity> transitionParcel(ParcelEntity parcel) throws BLValidationException;



}
