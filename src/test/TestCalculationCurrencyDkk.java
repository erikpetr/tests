package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyDkk {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 50 �re should make the display report 3 minutes parking time.
	 */
	@Test
	public void shouldDisplay3MinFor50Ore() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 3;	// In minutes
		int coinValue = 50;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
			
		// Assert
		assertEquals("Should display 3 min for 50 �re", expectedParkingTime, ps.readDisplay());
	}

	@Test
	public void shouldDisplay5334MinFor1000DKK() throws IllegalCoinException {

		// Arrange
		int expectedParkingTime = 5334;	// In minutes
		int coinValue = 20;
		int multiplier = 50;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

		// Act
		for (int i = 0; i < multiplier; i++) {
			ps.addPayment(coinValue, coinCurrency, coinType);
		}


		// Assert
		assertEquals("Should display 5334 min for 1000 DKK", expectedParkingTime, ps.readDisplay());
	}

	@Test
	public void shouldDisplay30MinFor5DKK50Ore() throws IllegalCoinException {

		// Arrange
		int expectedParkingTime = 30;	// In minutes
		int coinValueDkk = 5;
		Currency.ValidCurrency coinCurrencyDkk = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinTypeDkk = Currency.ValidCoinType.INTEGER;

		int coinValueOre = 50;
		Currency.ValidCurrency coinCurrencyOre = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinTypeOre = Currency.ValidCoinType.FRACTION;
		// Act
		ps.addPayment(coinValueDkk,coinCurrencyDkk,coinTypeDkk);
		ps.addPayment(coinValueOre,coinCurrencyOre,coinTypeOre);


		// Assert
		assertEquals("Should display 30 min for 5.50 DKK", expectedParkingTime, ps.readDisplay());
	}




	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		ps.setReady();
	}	
	
}
