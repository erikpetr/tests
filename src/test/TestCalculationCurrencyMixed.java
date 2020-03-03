package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B?rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyMixed {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 1 cent and 50 ?re should make the display report 4 minutes parking time.
	 */
	@Test
	public void shouldDisplay4MinFor1CentAnd1Ore() throws IllegalCoinException {
		int expectedParkingTime = 4;	// In minutes
		
		// Arrange
		
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		
		// Arrange
		coinValue = 50;
		coinCurrency = Currency.ValidCurrency.DKK;
		coinType = Currency.ValidCoinType.FRACTION;

		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		
		// Assert
		assertEquals("Should display 4 minutes for 1 cents and 50 ore", expectedParkingTime, ps.readDisplay());
	}

	
	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		ps.setReady();
	}
	
}
