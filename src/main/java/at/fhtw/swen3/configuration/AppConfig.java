package at.fhtw.swen3.configuration;

import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import at.fhtw.swen3.services.validation.InputValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ParcelService parcelService(InputValidator validator, ParcelRepository parcelRepository) {
        return new ParcelServiceImpl(validator, parcelRepository);
    }

    @Bean
    public WarehouseService warehouseService(InputValidator validator, WarehouseRepository warehouseRepository, TruckRepository truckRepository, TransferwarehouseRepository tansferwarehouseRepository, HopRepository hopRepository) {
        return new WarehouseServiceImpl(validator, warehouseRepository, truckRepository, tansferwarehouseRepository, hopRepository);
    }
}
