package test;

import static org.junit.Assert.*;

import org.junit.*;
import java.time.LocalDate;

//import controllayer.ControlPayStation;
//import controllayer.Currency;
//import controllayer.IPayStation;
//import controllayer.IReceipt;
//import controllayer.IllegalCoinException;

import databaselayer.*;
import modellayer.*;
import controllayer.*;

//import static org.junit.Assert.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B?rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestDatabaseAccess {
	
	DBConnection con = null;
	static PBuy tempPBuy;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		con = DBConnection.getInstance();
	}
	
	
	@Test
	public void wasConnected() {
		assertNotNull("Connected - connection cannot be null", con);
		
		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue("Disconnected - instance set to null", wasNullified);
		
		con = DBConnection.getInstance();
		assertNotNull("Connected - connection cannot be null", con);		
	}
	
	
	@Test
	public void wasInsertedBuy() {
		
		// Arrange
		LocalDate timeNow = java.time.LocalDate.now();
		double payedCentAmount = 100;
		
		tempPBuy = new PBuy();
		
		PPayStation pStat = new PPayStation(1, "P-423E");
		pStat.setAmount(payedCentAmount);
		tempPBuy.setAssociatedPaystation(pStat);
		tempPBuy.setBuyTime(timeNow);
		
		DatabasePBuy dbPbuy = new DatabasePBuy();
		
		// Act
		int key;
		
		try {
			key = dbPbuy.insertParkingBuy(tempPBuy);
			// Assert
			assertTrue("Key should be greater than 0 (no error)", key > 0);

			tempPBuy.setId(key);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * WARNING : The price at the idzonw 2 can change
	 */
	@Test
	public void wasRetrievedPriceDatabaselayer() {
		// Arrange
		PPrice foundPrice;
		int pZoneId = 2;
		DatabasePPrice dbPrice = new DatabasePPrice();

		
		// Act
		try {
			foundPrice = dbPrice.getPriceByZoneId(pZoneId);

			// Assert
			assertNotNull("A PPrice should have been discovered", foundPrice);
			assertEquals("Found price should be equal to 25", foundPrice.getParkingPrice(), 25);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void wasRetrievedPriceControllayer() {
		// Arrange
		ControlPrice controlPrice = new ControlPrice();
		PPrice pPrice;
		
		// Act
		try {
			pPrice = controlPrice.getPriceRemote(2);

			// Assert
			assertEquals("Found price should be equal to 25", 25, pPrice.getParkingPrice());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	
	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		DBConnection.closeConnection();
	}	
	
	@AfterClass
	public static void cleanUpWhenFinish() {
		// 		
		// Arrange
		DatabasePBuy dbPbuy = new DatabasePBuy();
		int numDeleted;
		
		// Act
		try {
			numDeleted = dbPbuy.deleteParkingBuy(tempPBuy);

			// Assert
			assertEquals("One row deleted", 1, numDeleted );
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		} finally {
			DBConnection.closeConnection();
		}
	}	

}
