package controllayer;

import databaselayer.DatabaseLayerException;
import modellayer.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingBuyTest {
    private ControlPayStation payStation;

    @Before
    public void setUp() throws Exception {
        this.payStation = new ControlPayStation();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void buyValid() {
        try {
            payStation.addPayment(2, Currency.ValidCurrency.EURO, Currency.ValidCoinType.INTEGER);
            PReceipt receipt = payStation.buy();
            assertEquals(80, receipt.getValue());
        } catch (DatabaseLayerException | IllegalCoinException e) {
            e.printStackTrace();
        }
    }
}
