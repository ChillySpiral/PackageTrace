package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.StateEnum;
import at.fhtw.swen3.services.validation.InputValidator;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    private final InputValidator validator;
    private final ParcelRepository parcelRepository;

    @Override
    public Optional<ParcelEntity> submitParcel(ParcelEntity parcel) {
        validator.validate(parcel);

        parcel.setTrackingId("PYJRB4HZ6");

        parcelRepository.save(parcel);

        return Optional.of(parcel);
    }

    @Override
    public Optional<ParcelEntity> trackParcel(String trackingId) {
        ParcelEntity result = parcelRepository.findByTrackingId(trackingId);

        return Optional.ofNullable(result);
    }

    @Override
    public boolean reportParcelDelivery(String trackingId) {
        ParcelEntity result = parcelRepository.findByTrackingId(trackingId);

        if(result != null) {
            result.setState(StateEnum.DELIVERED);
            parcelRepository.save(result);
            return true;
        }

        return false;
    }

    @Override
    public boolean reportParcelHop(String trackingId, String code) {
        ParcelEntity result = parcelRepository.findByTrackingId(trackingId);

        if(result != null) {
            //Todo find hop by code and remove from future add to visited
            return true;
        }

        return false;
    }

    public Optional<ParcelEntity> transitionParcel(ParcelEntity parcel) {
        validator.validate(parcel);

        parcelRepository.save(parcel);

        return Optional.of(parcel);
    }
}
