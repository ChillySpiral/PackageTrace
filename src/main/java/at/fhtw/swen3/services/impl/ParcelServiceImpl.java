package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.StateEnum;
import at.fhtw.swen3.services.validation.InputValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j 
public class ParcelServiceImpl implements ParcelService {

    private final InputValidator validator;
    private final ParcelRepository parcelRepository;

    @Override
    public ParcelEntity submitParcel(ParcelEntity parcel) {
        log.info("called submitParcel with parcel " + parcel.toString());

        validator.validate(parcel);
        log.info("validated parcel " + parcel.toString());

        parcel.setTrackingId("PYJRB4HZ6");
        log.info("set trackingId to PYJRB4HZ6 for parcel " + parcel.toString());

        parcelRepository.save(parcel);

        return parcel;
    }

    @Override
    public ParcelEntity trackParcel(String trackingId) {
        log.info("called trackParcel with trackingId " + trackingId);

        ParcelEntity result = parcelRepository.findByTrackingId(trackingId);
        log.info("returning result as ParcelEntity");

        return result;
    }

    @Override
    public boolean reportParcelDelivery(String trackingId) {
        log.info("called reportParcelDelivery with trackingId " + trackingId);

        ParcelEntity result = parcelRepository.findByTrackingId(trackingId);

        if(result != null) {
            log.info("ParcelEntity with trackingId " + trackingId + " was found, returning true");
            result.setState(StateEnum.DELIVERED);
            parcelRepository.save(result);
            return true;
        }
        else {
            log.info("ParcelEntity with trackingId " + trackingId + " could not be found, returning false");
            return false;
        }
    }

    @Override
    public boolean reportParcelHop(String trackingId, String code) {
        log.info("called reportParcelHop with trackingId " + trackingId + " and code " + code);

        ParcelEntity result = parcelRepository.findByTrackingId(trackingId);

        if(result != null) {
            //Todo find hop by code and remove from future add to visited
            log.info("ParcelEntity with trackingId " + trackingId + " was found, returning true");
            return true;
        }
        else {
            log.info("ParcelEntity with trackingId " + trackingId + " could not be found, returning false");
            return false;
        }
    }

    public ParcelEntity transitionParcel(ParcelEntity parcel) {
        log.info("called transitionParcel with parcel " + parcel.toString());

        validator.validate(parcel);
        log.info("validated parcel " + parcel.toString());

        parcelRepository.save(parcel);

        log.info("returning parcel");
        return parcel;
    }
}
