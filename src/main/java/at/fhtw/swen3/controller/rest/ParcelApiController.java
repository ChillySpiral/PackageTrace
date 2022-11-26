package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.mapper.ParcelMapper;
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
public class ParcelApiController implements ParcelApi {

    private NativeWebRequest request;

    private final ParcelService service;

    @Autowired
    public ParcelApiController(ParcelService service) {
        this.service = service;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(String trackingId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> reportParcelHop(String trackingId, String code) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel, TrackingInformation.builder().state(StateEnum.PICKUP).futureHops(new ArrayList<>()).visitedHops(new ArrayList<>()).build(), new NewParcelInfo());

        ParcelEntity result =  service.submitParcel(parcelEntity);

        NewParcelInfo newParcelInfo = ParcelMapper.INSTANCE.entityToNewParcelInfoDto(result);

        return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(String trackingId) {
        ParcelEntity result =  service.trackParcel(trackingId);

        if(null != result){
            TrackingInformation trackingInformation = ParcelMapper.INSTANCE.entityToTrackingInformationDto(result);
            return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(String trackingId, Parcel parcel) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
