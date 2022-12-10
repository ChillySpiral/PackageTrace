package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        log.info("called reportParcelDelivery with trackingId " + trackingId);

        boolean success =  service.reportParcelDelivery(trackingId);

        if(success){
            log.info("reportParcelDelivery with trackingId " + trackingId + " was successfully executed, HttpStatus.OK");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            log.info("reportParcelDelivery with trackingId " + trackingId + " could not be executed, HttpStatus.OK");
            return new ResponseEntity<>(HttpStatus.OK);
            /* ToDo: Activate with Sprint 4
            log.info("reportParcelDelivery with trackingId " + trackingId + " could not be executed, HttpStatus.NOT_FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             */
        }

    }

    @Override
    public ResponseEntity<Void> reportParcelHop(String trackingId, String code) {
        log.info("called reportParcelHop with trackingId " + trackingId + " and code " + code);

        boolean success =  service.reportParcelHop(trackingId, code);


        if(success){
            log.info("reportParcelHop with trackingId " + trackingId + " and code " + code + " was successfully executed, HttpStatus.OK");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            log.info("reportParcelHop with trackingId " + trackingId + " and code " + code + " could not be executed, HttpStatus.OK");
            return new ResponseEntity<>(HttpStatus.OK);
            /* ToDo: Activate with Sprint 4
            log.info("reportParcelHop with trackingId " + trackingId + " and code " + code + " could not be executed, HttpStatus.NOT_FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             */
        }


    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        log.info("called submitParcel with parcel " + parcel.toString());

        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel, TrackingInformation.builder().state(StateEnum.PICKUP).futureHops(new ArrayList<>()).visitedHops(new ArrayList<>()).build(), new NewParcelInfo());

        ParcelEntity result =  service.submitParcel(parcelEntity);

        if(null != result){
            log.info("submitParcel with parcel " + parcel.toString() + " was successfully executed, HttpStatus.CREATED");
            NewParcelInfo newParcelInfo = ParcelMapper.INSTANCE.entityToNewParcelInfoDto(result);
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
        }

        //ToDo: Change with Sprint 4
        // Todo: as error?
        log.info("submitParcel with parcel " + parcel.toString() + " could not be executed, HttpStatus.CREATED");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(String trackingId) {
        log.info("called trackParcel with trackingId " + trackingId);

        ParcelEntity result =  service.trackParcel(trackingId);


        if(null != result){
            log.info("trackParcel with trackingId " + trackingId + " was successfully executed, HttpStatus.OK");
            TrackingInformation trackingInformation = ParcelMapper.INSTANCE.entityToTrackingInformationDto(result);
            return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.OK);
        }
        else {
            log.info("trackParcel with trackingId " + trackingId + " coudl not be executed, HttpStatus.OK");
            return new ResponseEntity<>(HttpStatus.OK);
            /* ToDo: Activate with Sprint 4
            log.info("trackParcel with trackingId " + trackingId + " could not be executed, HttpStatus.NOT_FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             */
        }

    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(String trackingId, Parcel parcel) {
        log.info("called transitionParcel with trackingId " + trackingId + " and parcel " + parcel.toString());

        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel, TrackingInformation.builder().state(StateEnum.PICKUP).futureHops(new ArrayList<>()).visitedHops(new ArrayList<>()).build(), new NewParcelInfo(trackingId));

        ParcelEntity result =  service.transitionParcel(parcelEntity);

        if(null != result){
            log.info("transitionParcel with trackingId " + trackingId + " and parcel " + parcel.toString() + " was successfully executed, HttpStatus.OK");
            NewParcelInfo newParcelInfo = ParcelMapper.INSTANCE.entityToNewParcelInfoDto(result);
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.OK);
        }
        else {
            //ToDo: Change with Sprint 4
            log.info("transitionParcel with trackingId " + trackingId + " and parcel " + parcel.toString() + " was successfully executed, HttpStatus.OK");
            return new ResponseEntity<>(HttpStatus.OK);
            /*log.info("transitionParcel with trackingId " + trackingId + " and parcel " + parcel.toString() + " coldl not be executed, HttpStatus.NOT_FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);*/
        }

    }

}
