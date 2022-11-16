package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ParcelMapperTest {
    private static final Logger log = LoggerFactory.getLogger(ParcelMapperTest.class);

    @Test
    @DisplayName("Parcel Entity: DTO to Entity")
    void dtoToEntity(){
        final Recipient recipient = new Recipient("Max","Johnstraße 15/3","1150","Vienna","Austria");
        final Recipient sender = new Recipient("Alice","Sun Avenue","9467","Dallas","USA");
        final Parcel parcel = new Parcel(3.0f, recipient, sender);
        final List<HopArrival> visitedHops = new ArrayList<HopArrival>(){
            {
                add(new HopArrival("123","Desc Vis1", OffsetDateTime.MAX));
                add(new HopArrival("456","Desc Vis2", OffsetDateTime.MAX));
            }
        };
        final List<HopArrival> futureHops = new ArrayList<HopArrival>(){
            {
                add(new HopArrival("789","Desc Fut1", OffsetDateTime.MAX));
                add(new HopArrival("666","Desc Fut2", OffsetDateTime.MAX));
            }
        };
        final TrackingInformation trackingInformation = new TrackingInformation(StateEnum.INTRANSPORT, visitedHops, futureHops);
        final NewParcelInfo newParcelInfo = new NewParcelInfo("7890239689234");

        ParcelEntity result = ParcelMapper.INSTANCE.dtoToEntity(parcel, trackingInformation, newParcelInfo);

        log.info(result.toString());
        //Parcel Info
        assertEquals(parcel.getWeight(), result.getWeight());

        assertEquals(parcel.getRecipient().getName(), result.getRecipient().getName());
        assertEquals(parcel.getRecipient().getCountry(), result.getRecipient().getCountry());
        assertEquals(parcel.getRecipient().getCity(), result.getRecipient().getCity());
        assertEquals(parcel.getRecipient().getPostalCode(), result.getRecipient().getPostalCode());
        assertEquals(parcel.getRecipient().getStreet(), result.getRecipient().getStreet());

        assertEquals(parcel.getSender().getName(), result.getSender().getName());
        assertEquals(parcel.getSender().getCountry(), result.getSender().getCountry());
        assertEquals(parcel.getSender().getCity(), result.getSender().getCity());
        assertEquals(parcel.getSender().getPostalCode(), result.getSender().getPostalCode());
        assertEquals(parcel.getSender().getStreet(), result.getSender().getStreet());

        //TrackingInformation
        assertEquals(visitedHops.get(0).getCode(), result.getVisitedHops().get(0).getCode());
        assertEquals(visitedHops.get(0).getDescription(), result.getVisitedHops().get(0).getDescription());
        assertEquals(visitedHops.get(0).getDateTime(), result.getVisitedHops().get(0).getDateTime());
        assertEquals(visitedHops.get(1).getCode(), result.getVisitedHops().get(1).getCode());
        assertEquals(visitedHops.get(1).getDescription(), result.getVisitedHops().get(1).getDescription());
        assertEquals(visitedHops.get(1).getDateTime(), result.getVisitedHops().get(1).getDateTime());

        assertEquals(futureHops.get(0).getCode(), result.getFutureHops().get(0).getCode());
        assertEquals(futureHops.get(0).getDescription(), result.getFutureHops().get(0).getDescription());
        assertEquals(futureHops.get(0).getDateTime(), result.getFutureHops().get(0).getDateTime());
        assertEquals(futureHops.get(1).getCode(), result.getFutureHops().get(1).getCode());
        assertEquals(futureHops.get(1).getDescription(), result.getFutureHops().get(1).getDescription());
        assertEquals(futureHops.get(1).getDateTime(), result.getFutureHops().get(1).getDateTime());
        assertEquals(trackingInformation.getState(), result.getState());

        //New Parcel Info
        assertEquals(newParcelInfo.getTrackingId(), result.getTrackingId());
    }

    @Test
    @DisplayName("Parcel DTO: Entity to DTO")
    void entityToParcelDto() {
        final ParcelEntity parcelEntity = new ParcelEntity();
        final RecipientEntity recipient = new RecipientEntity(1L,"Max","Johnstraße 15/3","1150","Vienna","Austria");
        final RecipientEntity sender = new RecipientEntity(2L,"Alice","Sun Avenue","9467","Dallas","USA");
        parcelEntity.setRecipient(recipient);
        parcelEntity.setSender(sender);
        parcelEntity.setWeight(3.0f);

        Parcel result = ParcelMapper.INSTANCE.entityToParcelDto(parcelEntity);

        assertEquals(parcelEntity.getWeight(), result.getWeight());

        assertEquals(parcelEntity.getRecipient().getName(), result.getRecipient().getName());
        assertEquals(parcelEntity.getRecipient().getCountry(), result.getRecipient().getCountry());
        assertEquals(parcelEntity.getRecipient().getCity(), result.getRecipient().getCity());
        assertEquals(parcelEntity.getRecipient().getPostalCode(), result.getRecipient().getPostalCode());
        assertEquals(parcelEntity.getRecipient().getStreet(), result.getRecipient().getStreet());

        assertEquals(parcelEntity.getSender().getName(), result.getSender().getName());
        assertEquals(parcelEntity.getSender().getCountry(), result.getSender().getCountry());
        assertEquals(parcelEntity.getSender().getCity(), result.getSender().getCity());
        assertEquals(parcelEntity.getSender().getPostalCode(), result.getSender().getPostalCode());
        assertEquals(parcelEntity.getSender().getStreet(), result.getSender().getStreet());
    }

    @Test
    @DisplayName("New Parcel Info DTO: Entity to DTO")
    void entityToNewParcelInfoDto() {
        final ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setTrackingId("123456789");

        NewParcelInfo result = ParcelMapper.INSTANCE.entityToNewParcelInfoDto(parcelEntity);

        assertEquals(parcelEntity.getTrackingId(), result.getTrackingId());
    }

    @Test
    @DisplayName("New Tracking Information DTO: Entity to DTO")
    void entityToTrackingInformationDto() {
        final ParcelEntity parcelEntity = new ParcelEntity();
        final List<HopArrivalEntity> visitedHops = new ArrayList<HopArrivalEntity>(){
            {
                add(new HopArrivalEntity(1L,"123","Desc Vis1", OffsetDateTime.MAX, new ParcelEntity()));
                add(new HopArrivalEntity(2L,"456","Desc Vis2", OffsetDateTime.MAX, new ParcelEntity()));
            }
        };
        final List<HopArrivalEntity> futureHops = new ArrayList<HopArrivalEntity>(){
            {
                add(new HopArrivalEntity(3L,"789","Desc Fut1", OffsetDateTime.MAX, new ParcelEntity()));
                add(new HopArrivalEntity(4L,"666","Desc Fut2", OffsetDateTime.MAX, new ParcelEntity()));
            }
        };
        parcelEntity.setVisitedHops(visitedHops);
        parcelEntity.setFutureHops(futureHops);
        parcelEntity.setState(StateEnum.INTRANSPORT);

        TrackingInformation result = ParcelMapper.INSTANCE.entityToTrackingInformationDto(parcelEntity);

        assertEquals(visitedHops.get(0).getCode(), result.getVisitedHops().get(0).getCode());
        assertEquals(visitedHops.get(0).getDescription(), result.getVisitedHops().get(0).getDescription());
        assertEquals(visitedHops.get(0).getDateTime(), result.getVisitedHops().get(0).getDateTime());
        assertEquals(visitedHops.get(1).getCode(), result.getVisitedHops().get(1).getCode());
        assertEquals(visitedHops.get(1).getDescription(), result.getVisitedHops().get(1).getDescription());
        assertEquals(visitedHops.get(1).getDateTime(), result.getVisitedHops().get(1).getDateTime());

        assertEquals(futureHops.get(0).getCode(), result.getFutureHops().get(0).getCode());
        assertEquals(futureHops.get(0).getDescription(), result.getFutureHops().get(0).getDescription());
        assertEquals(futureHops.get(0).getDateTime(), result.getFutureHops().get(0).getDateTime());
        assertEquals(futureHops.get(1).getCode(), result.getFutureHops().get(1).getCode());
        assertEquals(futureHops.get(1).getDescription(), result.getFutureHops().get(1).getDescription());
        assertEquals(futureHops.get(1).getDateTime(), result.getFutureHops().get(1).getDateTime());
        assertEquals(parcelEntity.getState(), result.getState());
    }
}