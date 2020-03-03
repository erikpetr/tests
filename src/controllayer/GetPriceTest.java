package controllayer;

import databaselayer.DatabaseLayerException;
import modellayer.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetPriceTest {
    private ControlPrice price;

    @Before
    public void setUp() throws Exception {
        this.price = new ControlPrice();
    }

    @After
    public void tearDown() throws Exception {
        this.price = null;
    }

    @Test
    public void getPriceValidZoneId() {
        try {
            PPrice pPrice = price.getPriceRemote(1);
            assertEquals("Getting the price for zone 1 from the db should give 35",35, pPrice.getParkingPrice());
        } catch (DatabaseLayerException e) {
            e.printStackTrace();
        }
    }
}

