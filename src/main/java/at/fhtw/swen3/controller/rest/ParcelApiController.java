package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.StateEnum;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.exceptions.BLDataNotFoundException;
import at.fhtw.swen3.services.exceptions.BLValidationException;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Optional;

@Generated(value = "at.fhtw.swen3.openapitools.codegen.languages.SpringCodegen", date = "2022-10-14T19:35:25.076618Z[Etc/UTC]")
@Controller
@RequiredArgsConstructor
@Slf4j
public class ParcelApiController implements ParcelApi {

    private NativeWebRequest request;

    private final ParcelService service;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(String trackingId) {
        log.info("Report Parcel Delivery with trackingId " + trackingId);
        try {
            var success = service.reportParcelDelivery(trackingId);

            log.info("Completed: reportParcelDelivery with trackingId " + trackingId + " was successfully executed, HttpStatus.OK");
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (BLDataNotFoundException exp) {
            log.info("Parcel Delivery with trackingId: "+trackingId+ " could not be delivered because the corresponding parcel could not be found, errormessage: " +  exp.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> reportParcelHop(String trackingId, String code) {
        log.info("Report Parcel Hop with trackingId " + trackingId + " and code " + code);
        try {
            var success = service.reportParcelHop(trackingId, code);

            log.info("Completed: reportParcelHop with trackingId " + trackingId + " and code " + code + " was successfully executed, HttpStatus.OK");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BLDataNotFoundException exp) {
            log.info("ParcelHop with trackingId: "+trackingId+ " could not be reported because the vorresponding hop could not be found, errormessage: " +  exp.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        log.info("Submit Parcel " + parcel.toString());
        try {
            var parcelEntity = mapRegisterNewParcel(parcel);
            var result = service.submitParcel(parcelEntity);

            log.info("Completed: submitParcel with parcel " + parcel.toString() + " was successfully executed, HttpStatus.CREATED");
            var newParcelInfo = ParcelMapper.INSTANCE.entityToNewParcelInfoDto(result.get());
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);

        } catch (BLValidationException exp) {
            log.info("Parcel with parcel "+parcel.toString()+" could not be submitted because validation failed with errormessage " +  exp.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(String trackingId) {
        log.info("Track Parcel with trackingId " + trackingId);
        try {
            var result = service.trackParcel(trackingId);

            log.info("Completed: trackParcel with trackingId " + trackingId + " was successfully executed, HttpStatus.OK");
            TrackingInformation trackingInformation = ParcelMapper.INSTANCE.entityToTrackingInformationDto(result.get());
            return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.OK);

        } catch (BLDataNotFoundException exp) {
            log.error("Parcel with trackingid "+trackingId+" could not be found, errormessage: " +  exp.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(String trackingId, Parcel parcel) {
        log.info("Transition Parcel: "+ parcel.toString()+  " with trackingId " + trackingId);
        try {
            var parcelEntity = mapRegisterNewParcel(parcel, trackingId);
            var result = service.transitionParcel(parcelEntity);

            log.info("Completed: transitionParcel with trackingId " + trackingId + " and parcel " + parcel.toString() + " was successfully executed, HttpStatus.OK");
            var newParcelInfo = ParcelMapper.INSTANCE.entityToNewParcelInfoDto(result.get());
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.OK);

        } catch (BLValidationException exp) {
            log.info("Parcel with parcel " + parcel + " could not be transitioned because validation failed with errormessage " + exp);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private ParcelEntity mapRegisterNewParcel(Parcel parcel) {
        var parcelInfo = new NewParcelInfo();
        var trackingInfo = TrackingInformation.builder().state(StateEnum.UNKNOWN).futureHops(new ArrayList<>()).visitedHops(new ArrayList<>()).build();
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel, trackingInfo, parcelInfo);
        return parcelEntity;
    }

    private ParcelEntity mapRegisterNewParcel(Parcel parcel, String trackingId) {
        var parcelInfo = NewParcelInfo.builder().trackingId(trackingId).build();
        var trackingInfo = TrackingInformation.builder().state(StateEnum.UNKNOWN).futureHops(new ArrayList<>()).visitedHops(new ArrayList<>()).build();
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel, trackingInfo, parcelInfo);
        return parcelEntity;
    }

}
