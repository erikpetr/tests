package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B?rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyEuro {

	private ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}
	
	/**
	 * Entering no coins should make the display report 0 minutes parking time.
	 */
	@Test
	public void shouldDisplay0() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 0;
		
		// Act
		// No action
		
		// Assert
		assertEquals("Should display 0 min for no coins", expectedParkingTime, ps.readDisplay());
	}	

	/**
	 * Entering 5 cents should make the display report 2 minutes parking time
	 */
	@Test
	public void shouldDisplay2MinFor5Cents() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 2;	// In minutes		
		int coinValue = 5;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		
		// Assert
		assertEquals("Should display 2 minutes for 5 cents", expectedParkingTime, ps.readDisplay());
	}

	@Test
	public void shouldDisplay40000MinFor1000Eur() throws IllegalCoinException {

		// Arrange
		int expectedParkingTime = 40000;	// In minutes
		int coinValue = 2;
		int multiplier = 500;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

		// Act
		for (int i = 0; i < multiplier; i++) {
			ps.addPayment(coinValue, coinCurrency, coinType);
		}


		// Assert
		assertEquals("Should display 40 000 min for 1000 EUR", expectedParkingTime, ps.readDisplay());
	}

	@Test
	public void shouldDisplay212MinFor5Eur30Cents() throws IllegalCoinException {

		// Arrange
		int expectedParkingTime = 212;	// In minutes
		int coinValue = 5;
		//Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		//Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;

		// Act
		ps.addPayment(2, Currency.ValidCurrency.EURO, Currency.ValidCoinType.INTEGER);
		ps.addPayment(2, Currency.ValidCurrency.EURO, Currency.ValidCoinType.INTEGER);
		ps.addPayment(1, Currency.ValidCurrency.EURO, Currency.ValidCoinType.INTEGER);
		ps.addPayment(10, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);
		ps.addPayment(20, Currency.ValidCurrency.EURO, Currency.ValidCoinType.FRACTION);

		// Assert
		assertEquals("Should display 212 minutes for 5 eur and 50 cents", expectedParkingTime, ps.readDisplay());
	}

	/**
	 * Cleanup after each test
	 */
	@After
	public void cleanUp () {
		ps.cancel();
	}
	
}
