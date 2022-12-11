package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.StateEnum;
import at.fhtw.swen3.services.validation.InputValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {

    private final InputValidator validator;
    private final ParcelRepository parcelRepository;

    @Override
    public Optional<ParcelEntity> submitParcel(ParcelEntity parcel) {
        log.info("Validating Parcel " + parcel.toString());
        validator.validate(parcel);

        //ToDo: Generate New TrackingID
        log.info("Set trackingId to PYJRB4HZ6 for parcel " + parcel);
        parcel.setTrackingId("PYJRB4HZ6");

        parcelRepository.save(parcel);

        return Optional.of(parcel);

        //ToDo: Sprint 5: Error Handling via Exception
    }

    @Override
    public Optional<ParcelEntity> trackParcel(String trackingId) {
        log.info("Tracking Parcel with TrackingId: " + trackingId);

        var result = parcelRepository.findByTrackingId(trackingId);

        return Optional.ofNullable(result);

        //ToDo: Sprint 5: Error Handling via Exception
    }

    @Override
    public boolean reportParcelDelivery(String trackingId) {
        log.info("Reporting Parcel Delivery with TrackingId " + trackingId);

        var result = parcelRepository.findByTrackingId(trackingId);

        if(result != null) {
            log.info("Setting State of ParcelEntity with trackingId " + trackingId + " to DELIVERED");
            result.setState(StateEnum.DELIVERED);
            parcelRepository.save(result);
            return true;
        }
        else {
            log.info("ParcelEntity with trackingId " + trackingId + " could not be found");
            return false;
        }

        //ToDo: Sprint 5: Error Handling via Exception
    }

    @Override
    public boolean reportParcelHop(String trackingId, String code) {
        log.info("Reporting Parcel Hop for Parcel with trackingId " + trackingId + " to code " + code);

        var result = parcelRepository.findByTrackingId(trackingId);

        if(result != null) {

            var reachedHop = result.getFutureHops().stream().filter(x->x.getCode().equals(code)).findFirst();
            if(reachedHop.isPresent()){
                result.getVisitedHops().add(reachedHop.get());
                result.getFutureHops().remove(reachedHop.get());
                parcelRepository.save(result);
            } else{
                log.error("Hop with code: " + code + " not found");
                //ToDo: Sprint 5: Error Handling via Exception
            }

            log.info("ParcelEntity with trackingId " + trackingId + " was found, returning true");
            return true;
        }
        else {
            log.info("ParcelEntity with trackingId " + trackingId + " could not be found, returning false");
            return false;
        }
        //ToDo: Sprint 5: Error Handling via Exception
    }

    public Optional<ParcelEntity> transitionParcel(ParcelEntity parcel) {
        log.info("Transition Parcel: " + parcel.toString());

        log.info("Validating Parcel: " + parcel);
        validator.validate(parcel);

        log.info("Saving Parcel " + parcel);
        parcelRepository.save(parcel);

        return Optional.of(parcel);

        //ToDo: Sprint 5: Error Handling via Exception
    }
}
