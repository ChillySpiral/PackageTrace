package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.StateEnum;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
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

            if (success) {
                log.info("Completed: reportParcelDelivery with trackingId " + trackingId + " was successfully executed, HttpStatus.OK");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                //ToDo Sprint 5: Exception Handling/Error Handling
                log.warn("Unexpected behavior occured: Result not present but no exception was thrown");
                throw new Exception("Unknown Error");
            }
        } catch (Exception exp) {
            log.error("Request Parameters: trackingId: "+trackingId+ " Message: " +  exp.getMessage());
            //ToDo: Sprint 5 Return Error Information
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> reportParcelHop(String trackingId, String code) {
        log.info("Report Parcel Hop with trackingId " + trackingId + " and code " + code);
        try {
            var success = service.reportParcelHop(trackingId, code);

            if (success) {
                log.info("Completed: reportParcelHop with trackingId " + trackingId + " and code " + code + " was successfully executed, HttpStatus.OK");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                //ToDo Sprint 5: Exception Handling/Error Handling
                log.warn("Unexpected behavior occured: Result not present but no exception was thrown");
                throw new Exception("Unknown Error");

            }
        } catch (Exception exp) {
            log.error("Request Parameters: trackingId: "+trackingId+" code:"+ code +" Message: " +  exp.getMessage());
            //ToDo: Sprint 5 Return Error Information
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        log.info("Submit Parcel " + parcel.toString());
        try {
            var parcelEntity = mapRegisterNewParcel(parcel);
            var result = service.submitParcel(parcelEntity);

            if (result.isPresent()) {
                log.info("Completed: submitParcel with parcel " + parcel.toString() + " was successfully executed, HttpStatus.CREATED");
                var newParcelInfo = ParcelMapper.INSTANCE.entityToNewParcelInfoDto(result.get());
                return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
            } else {
                //ToDo Sprint 5: Exception Handling/Error Handling
                log.warn("Unexpected behavior occured: Result not present but no exception was thrown");
                throw new Exception("Unknown Error");
            }
        } catch (Exception exp) {
            log.error("Request Parameters: parcel: "+parcel.toString()+" Message: " +  exp.getMessage());
            //ToDo Sprint 5: Return Error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(String trackingId) {
        log.info("Track Parcel with trackingId " + trackingId);
        try {
            var result = service.trackParcel(trackingId);

            if (result.isPresent()) {
                log.info("Completed: trackParcel with trackingId " + trackingId + " was successfully executed, HttpStatus.OK");
                TrackingInformation trackingInformation = ParcelMapper.INSTANCE.entityToTrackingInformationDto(result.get());
                return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.OK);
            } else {
                //ToDo Sprint 5: Exception Handling/Error Handling
                log.warn("Unexpected behavior occured: Result not present but no exception was thrown");
                throw new Exception("Unknown Error");
            }
        } catch (Exception exp) {
            log.error("Request Parameters: trackingId: "+trackingId+" Message: " +  exp.getMessage());
            //ToDo Sprint 5: Return Error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(String trackingId, Parcel parcel) {
        log.info("Transition Parcel: "+ parcel.toString()+  " with trackingId " + trackingId);
        try {
            var parcelEntity = mapRegisterNewParcel(parcel, trackingId);
            var result = service.transitionParcel(parcelEntity);

            if (result.isPresent()) {
                log.info("Completed: transitionParcel with trackingId " + trackingId + " and parcel " + parcel.toString() + " was successfully executed, HttpStatus.OK");
                var newParcelInfo = ParcelMapper.INSTANCE.entityToNewParcelInfoDto(result.get());
                return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.OK);
            } else {
                //ToDo Sprint 5: Exception Handling/Error Handling
                log.warn("Unexpected behavior occured: Result not present but no exception was thrown");
                throw new Exception("Unknown Error");
            }
        } catch (Exception exp) {
            log.error("Request Parameters: trackingId: "+trackingId+ " parcel: "+parcel.toString()+" Message: " +  exp.getMessage());
            //ToDo Sprint 5: Return Error
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
