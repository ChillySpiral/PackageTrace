package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WarehouseServiceImplTest {

    @Autowired
    public WarehouseService service;

    public static WarehouseEntity entityStructure;

    private static HopRepository repository;

    @Autowired
    private HopRepository repositoryInit;

    @PostConstruct
    private void initStaticDao () {
        repository = this.repositoryInit;
    }
    @AfterAll
    public static void cleanup(){
        var hopOne = repository.findByCode("WTTA089");

        repository.delete(hopOne);
    }

    @BeforeAll
    public static void initWarehouseStructure(){
        var geoCoordinates = GeoCoordinateEntity.builder()
                .lon(12D)
                .lat(17D)
                .build();

        var truck = TruckEntity.builder()
                .description("Test Truck")
                .hopType("truck")
                .locationCoordinates(geoCoordinates)
                .processingDelayMins(34)
                .numberPlate("W2323XG")
                .code("WTTA01")
                .build();

        var transferWarehouse = TransferwarehouseEntity.builder()
                .description("Test TransferWarehouse")
                .hopType("transferWarehouse")
                .code("WENB01")
                .locationCoordinates(geoCoordinates)
                .locationName("Wien Zentral")
                .logisticsPartner("Hermes")
                .logisticsPartnerUrl("hermes.at")
                .processingDelayMins(56)
                .regionGeoJson("")
                .build();

        var truckHop = WarehouseNextHopsEntity.builder()
                .hop(truck)
                .traveltimeMins(123)
                .build();

        var transferHop = WarehouseNextHopsEntity.builder()
                .hop(transferWarehouse)
                .traveltimeMins(65)
                .build();

        var nextHops = new ArrayList<WarehouseNextHopsEntity>();

        nextHops.add(transferHop);
        nextHops.add(truckHop);

        var warehouse = WarehouseEntity.builder()
                .description("Info")
                .code("WTTA089")
                .hopType("warehouse")
                .locationCoordinates(geoCoordinates)
                .locationName("Station Westbahnhof")
                .processingDelayMins(123)
                .level(0)
                .nextHops(nextHops)
                .build();

        entityStructure = warehouse;
    }

    @Test
    @Order(1)
    public void importWarehouses(){
        //Arrange
        var warehouse = entityStructure;

        //Act
        var res = service.importWarehouses(warehouse);

        //Assert
        assertTrue(res);
    }

    @Test
    @Order(2)
    public void getWarehouses(){
        //Arrange
        var warehouseCode = "WTTA089";
        var truckCode = "WTTA01";
        var transferWarehouseCode = "WENB01";

        //Act
        var resWarehouse = service.getWarehouse(warehouseCode);
        var resTruck = service.getWarehouse(truckCode);
        var resTransferWarehouse = service.getWarehouse(transferWarehouseCode);

        //Assert
        assertTrue(resWarehouse.isPresent());
        assertTrue(resTruck.isPresent());
        assertTrue(resTransferWarehouse.isPresent());

        assertInstanceOf(WarehouseEntity.class, resWarehouse.get());
        assertInstanceOf(TruckEntity.class, resTruck.get());
        assertInstanceOf(TransferwarehouseEntity.class, resTransferWarehouse.get());

        assertEquals(entityStructure.toString(), resWarehouse.get().toString());
        assertEquals(entityStructure.getNextHops().get(0).getHop().toString().toString(), resTransferWarehouse.get().toString());
        assertEquals(entityStructure.getNextHops().get(1).getHop().toString(), resTruck.get().toString());
    }

    @Test
    @Order(3)
    public void exportWarehouses(){
        //Arrange

        //Act
        var res = service.exportWarehouses();

        //Assert
        assertTrue(res.isPresent());
        assertInstanceOf(WarehouseEntity.class, res.get());
        assertEquals(entityStructure.toString(), res.get().toString());
    }
}