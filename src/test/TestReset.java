package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B?rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestReset {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that the pay station is cleared and display shows 0 after a buy scenario
	 */
	@Test
	public void shouldClearAfterBuy() throws IllegalCoinException, Exception {
		int amount = 2;
		Currency.ValidCurrency currency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		ps.addPayment(amount,currency, coinType);
		
		amount = 1;
		currency = Currency.ValidCurrency.EURO;
		coinType = Currency.ValidCoinType.INTEGER;
		ps.addPayment(amount,currency, coinType);
		
		PReceipt receipt = ps.buy();
		assertEquals(ps.readDisplay(), 0);
	}

	/**
	 * Verify that cancel() clears the pay station
	 */
	@Test
	public void shouldClearAfterCancel() throws IllegalCoinException {
		ps.cancel();
		assertEquals(ps.readDisplay(), 0);
	}
}
