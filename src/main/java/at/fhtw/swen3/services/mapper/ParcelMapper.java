package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParcelMapper {
    ParcelMapper INSTANCE = Mappers.getMapper(ParcelMapper.class);

    @Mapping(source = "parcel.weight", target = "weight")
    @Mapping(source = "parcel.recipient", target = "recipient")
    @Mapping(source = "parcel.sender", target = "sender")
    @Mapping(source = "newParcelInfo.trackingId", target = "trackingId")
    @Mapping(source = "trackingInformation.state", target = "state")
    @Mapping(source = "trackingInformation.visitedHops", target = "visitedHops")
    @Mapping(source = "trackingInformation.futureHops", target = "futureHops")
    ParcelEntity dtoToEntity(Parcel parcel, TrackingInformation trackingInformation, NewParcelInfo newParcelInfo);

    @Mapping(source = "parcelEntity.weight", target = "weight")
    @Mapping(source = "parcelEntity.recipient", target = "recipient")
    @Mapping(source = "parcelEntity.sender", target = "sender")
    Parcel entityToParcelDto(ParcelEntity parcelEntity);

    @Mapping(source = "parcelEntity.state", target = "state")
    @Mapping(source = "parcelEntity.visitedHops", target = "visitedHops")
    @Mapping(source = "parcelEntity.futureHops", target = "futureHops")
    TrackingInformation entityToTrackingInformationDto(ParcelEntity parcelEntity);

    @Mapping(source = "parcelEntity.trackingId", target = "trackingId")
    NewParcelInfo entityToNewParcelInfoDto(ParcelEntity parcelEntity);
}
