package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.StateEnum;
import at.fhtw.swen3.services.BLDataNotFoundException;
import at.fhtw.swen3.services.BLValidationException;
import at.fhtw.swen3.persistence.DALDataNotFondException;
import at.fhtw.swen3.services.validation.InputValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {

    private final InputValidator validator;
    private final ParcelRepository parcelRepository;

    @Override
    public Optional<ParcelEntity> submitParcel(ParcelEntity parcel) throws BLValidationException {
        try {
            log.info("Validating Parcel " + parcel.toString());
            validator.validate(parcel);

            //ToDo: Generate New TrackingID
            log.info("Set trackingId to PYJRB4HZ6 for parcel " + parcel);
            parcel.setTrackingId("PYJRB4HZ6");

            parcelRepository.save(parcel);

            return Optional.of(parcel);
        } catch (ConstraintViolationException exp) {
            log.info("Service: Submit Parcel " + parcel + " failed wit errormessage: " +  exp.getMessage());
            throw new BLValidationException(exp.getMessage(), "submitParcel", "ParcelServiceImpl", exp.getConstraintViolations().toString());
        }

    }

    @Override
    public Optional<ParcelEntity> trackParcel(String trackingId) throws BLDataNotFoundException {

        try {
            log.info("Tracking Parcel with TrackingId: " + trackingId);

            var result = parcelRepository.findByTrackingId(trackingId);

            if(null == result) {
                throw new DALDataNotFondException("No Parcel could be found", "findByTrackingId", "ParcelRepository");
            }
            return Optional.ofNullable(result);

        } catch (DALDataNotFondException exp) {
            log.info("Service: Export Warehouses failed with errormessage: " +  exp.getMessage());
            throw new BLDataNotFoundException(exp.toString(), "trackParcel", "ParcelServiceImpl");
        }
    }

    @Override
    public boolean reportParcelDelivery(String trackingId) throws BLDataNotFoundException {
        try {
            log.info("Reporting Parcel Delivery with TrackingId " + trackingId);

            var result = parcelRepository.findByTrackingId(trackingId);

            if(result != null) {
                log.info("Setting State of ParcelEntity with trackingId " + trackingId + " to DELIVERED");
                result.setState(StateEnum.DELIVERED);
                parcelRepository.save(result);
                return true;
            }
            else {
                throw new DALDataNotFondException("ParcelEntity with trackingId " + trackingId + " could not be found", "findByTrackingId", "ParcelRepository");
            }
        } catch (DALDataNotFondException exp) {
            log.info("Service: Report Parcel failed with errormessage: " + exp);
            throw new BLDataNotFoundException(exp.toString(), "trackParcel", "ParcelServiceImpl");
        }
    }

    @Override
    public boolean reportParcelHop(String trackingId, String code) throws BLDataNotFoundException {
        try {
            log.info("Reporting Parcel Hop for Parcel with trackingId " + trackingId + " to code " + code);

            var result = parcelRepository.findByTrackingId(trackingId);

            if (result != null) {

                var reachedHop = result.getFutureHops().stream().filter(x -> x.getCode().equals(code)).findFirst();
                if (reachedHop.isPresent()) {
                    result.getVisitedHops().add(reachedHop.get());
                    result.getFutureHops().remove(reachedHop.get());
                    parcelRepository.save(result);
                } else {
                    // Todo classname & function name not quite right
                    throw new DALDataNotFondException("Hop with code " + code + " could not be found.", "filter", "ParcelRepository");
                }

                log.info("ParcelEntity with trackingId " + trackingId + " was found, returning true");
                return true;
            } else {
                throw new DALDataNotFondException("ParcelEntity with trackingId " + trackingId + " could not be found", "findByTrackingId", "ParcelRepository");
            }
        } catch (DALDataNotFondException exp) {
            log.info("Service: Report Parcel failed with errormessage: " + exp);
            throw new BLDataNotFoundException(exp.toString(), "trackParcel", "ParcelServiceImpl");
        }
    }

    public Optional<ParcelEntity> transitionParcel(ParcelEntity parcel) throws BLValidationException {
        try {
            log.info("Transition Parcel: " + parcel.toString());

            log.info("Validating Parcel: " + parcel);
            validator.validate(parcel);

            log.info("Saving Parcel " + parcel);
            parcelRepository.save(parcel);

            return Optional.of(parcel);
        } catch (ConstraintViolationException exp) {
            log.info("Service: Transition Parcel " + parcel + " failed wit errormessage: " +  exp.getMessage());
            throw new BLValidationException(exp.getMessage(), "transitionParcel", "ParcelServiceImpl", exp.getConstraintViolations().toString());
        }
    }
}
