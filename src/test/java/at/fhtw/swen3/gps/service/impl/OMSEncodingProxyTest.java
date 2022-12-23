package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.impl.OMSEncodingProxy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled //Integration Test
@TestPropertySource("/application-test.properties")
class OMSEncodingProxyTest {

    @Test
    void encodeAddressStephansplatz() {
        var proxyService = new OMSEncodingProxy();

        var res = proxyService.encodeAddress("Stephansplatz");

        assertEquals(48.208419, res.getLat());
        assertEquals(16.3720045, res.getLon());
    }

    @Test
    void encodeAddressWels() {
        var proxyService = new OMSEncodingProxy();

        var res = proxyService.encodeAddress("Laahener Straße 7 Wels 4600");

        assertEquals(48.164243850000005, res.getLat());
        assertEquals(14.014416810246317, res.getLon());
    }
}