package controllayer;

import modellayer.Coin;
import modellayer.Currency;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControlPayStationTest {
    private ControlPayStation payStation;

    @Before
    public void setUp() throws Exception {
        this.payStation = new ControlPayStation();
    }

    @After
    public void tearDown() throws Exception {
        this.payStation = null;
    }

    @Test
    public void addPaymentAllValid() {
        int amount = 2;
        Currency.ValidCurrency currency = Currency.ValidCurrency.EURO;
        Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

        try {
            payStation.addPayment(amount, currency, coinType);
        } catch (IllegalCoinException e) {
            e.printStackTrace();
        }

        assertEquals("Adding an 2 EUR coin should display 80 minutes",80, payStation.readDisplay());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPaymentInvalidCoinType() throws IllegalArgumentException, IllegalCoinException {
        int amount = 20;
        Currency.ValidCurrency currency = Currency.ValidCurrency.DKK;
        Currency.ValidCoinType coinType = Currency.ValidCoinType.valueOf("Invalid");

        payStation.addPayment(amount, currency, coinType);
        System.out.println("Adding an invalid DKK coin type should throw an IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPaymentInvalidCurrency() throws IllegalArgumentException, IllegalCoinException {
        int amount = 50;
        Currency.ValidCurrency currency = Currency.ValidCurrency.valueOf("Dollar");
        Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

        payStation.addPayment(amount,currency,coinType);
        System.out.println("Adding an invalid currency - USD - should throw an IllegalArgumentException");
    }

    @Test(expected = IllegalCoinException.class)
    public void addPaymentInvalidAmount() throws IllegalCoinException {
        int amount = 30;
        Currency.ValidCurrency currency = Currency.ValidCurrency.DKK;
        Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

        payStation.addPayment(amount,currency,coinType);
        System.out.println("Adding a not existing DKK coin value should throw an IllegalCoinException");
    }

}