package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ParcelService {

    Optional<ParcelEntity> submitParcel(ParcelEntity parcel);

    Optional<ParcelEntity> trackParcel(String trackingId);

    boolean reportParcelDelivery(String trackingId);

    boolean reportParcelHop(String trackingId, String code);

    Optional<ParcelEntity> transitionParcel(ParcelEntity parcel);



}
