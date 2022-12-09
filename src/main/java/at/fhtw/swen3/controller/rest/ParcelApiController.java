package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.RequiredArgsConstructor;
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
public class ParcelApiController implements ParcelApi {

    private NativeWebRequest request;

    private final ParcelService service;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(String trackingId) {
        boolean success =  service.reportParcelDelivery(trackingId);



        if(success){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
        /* ToDo: Activate with Sprint 4
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         */
    }

    @Override
    public ResponseEntity<Void> reportParcelHop(String trackingId, String code) {
        boolean success =  service.reportParcelHop(trackingId, code);


        if(success){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.OK);
        /* ToDo: Activate with Sprint 4
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         */
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel, TrackingInformation.builder().state(StateEnum.PICKUP).futureHops(new ArrayList<>()).visitedHops(new ArrayList<>()).build(), new NewParcelInfo());

        ParcelEntity result =  service.submitParcel(parcelEntity);

        if(null != result){
            NewParcelInfo newParcelInfo = ParcelMapper.INSTANCE.entityToNewParcelInfoDto(result);
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
        }

        //ToDo: Change with Sprint 4
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(String trackingId) {
        ParcelEntity result =  service.trackParcel(trackingId);


        if(null != result){
            TrackingInformation trackingInformation = ParcelMapper.INSTANCE.entityToTrackingInformationDto(result);
            return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
        /* ToDo: Activate with Sprint 4
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         */
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(String trackingId, Parcel parcel) {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel, TrackingInformation.builder().state(StateEnum.PICKUP).futureHops(new ArrayList<>()).visitedHops(new ArrayList<>()).build(), new NewParcelInfo(trackingId));

        ParcelEntity result =  service.transitionParcel(parcelEntity);

        if(null != result){
            NewParcelInfo newParcelInfo = ParcelMapper.INSTANCE.entityToNewParcelInfoDto(result);
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.OK);
        }
        //ToDo: Change with Sprint 4
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
