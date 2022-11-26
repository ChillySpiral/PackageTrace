package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

public interface ParcelService {

    public ParcelEntity submitParcel(ParcelEntity parcel);

    public ParcelEntity trackParcel(String trackingId);

}
