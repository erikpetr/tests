package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B?rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestIllegalCoin {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that illegal coins are rejected.
	 */
	
	
	// Norwegian coin
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalCurrencyNokCoin() throws IllegalCoinException {
		System.out.println("When adding an 1 NOK coin as payment, it should throw an IllegalCoinException");
		int amount = 1;
		Currency.ValidCurrency currency = Currency.ValidCurrency.NOK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		ps.addPayment(amount,currency, coinType);
	}
	// unknown Euro coin value
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalEuroCoin() throws IllegalCoinException {
		System.out.println("When adding an 4 EUR coin as payment, it should throw an IllegalCoinException");
		int amount = 4;
		Currency.ValidCurrency currency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		ps.addPayment(amount,currency, coinType);
	}

	//Valid EUR coins and then USD
	@Test
	public void shouldThrowExceptionFor50Cents1Usd() {
		int expectedParkingTime = 20; // in minutes
		boolean usdFound = false;
		try {
			ps.addPayment(50, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
			ps.addPayment(1, Currency.ValidCurrency.USD, Currency.ValidCoinType.INTEGER);
			assertFalse(usdFound);
		} catch (IllegalCoinException e) {
			System.out.println("Illegally inserted coin found: \n" + e.getMessage());
			usdFound = true;
			assertTrue("When adding an USD coin, it should throw an IllegalCoinException", usdFound);
		}
	}
}