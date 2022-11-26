package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.validation.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;

@Service
public class ParcelServiceImpl implements ParcelService {

    private final InputValidator validator;
    private final ParcelRepository parcelRepository;

    @Autowired
    public ParcelServiceImpl(InputValidator validator, ParcelRepository parcelRepository) {
        this.validator = validator;
        this.parcelRepository = parcelRepository;
    }

    @Override
    public ParcelEntity submitParcel(ParcelEntity parcel) {
        validator.validate(parcel);

        parcel.setTrackingId("437898104");

        parcelRepository.save(parcel);

        return parcel;
    }

    @Override
    public ParcelEntity trackParcel(String trackingId) {
        ParcelEntity result = parcelRepository.findByTrackingId(trackingId);

        return result;
    }
}
