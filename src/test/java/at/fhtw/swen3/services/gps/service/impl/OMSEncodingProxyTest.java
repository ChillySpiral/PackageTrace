package at.fhtw.swen3.services.gps.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class OMSEncodingProxyTest {

    @Test
    void encodeAddress() {
        var proxyService = new OMSEncodingProxy();

        var res = proxyService.encodeAddress("Stephansplatz");

        assertEquals(48.208419, res.getLat());
        assertEquals(16.3720045, res.getLon());
    }
}