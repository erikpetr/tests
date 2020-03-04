package controllayer;

import databaselayer.DatabaseLayerException;
import databaselayer.IDbPPrice;
import databaselayer.DatabasePPrice;
import modellayer.PPrice;

public class ControlPrice {
	
	private IDbPPrice dbPrice;

	private static PPrice currentPrice;
	
	public ControlPrice() {
		this.dbPrice = new DatabasePPrice();
	}
	
	public PPrice getCurrentPrice() {
		if (currentPrice == null) {
			return new PPrice();
		}
		return currentPrice;
	}
	
	public PPrice getPriceRemote(int zoneId) throws DatabaseLayerException {
		
		// Get price from Parkingsystem DB
		PPrice readPrice = dbPrice.getPriceByZoneId(zoneId);
		currentPrice = readPrice;
		//
		return readPrice;
	}

}
