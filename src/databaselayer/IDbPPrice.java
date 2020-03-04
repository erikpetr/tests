package databaselayer;

import modellayer.PPrice;

public interface IDbPPrice {

	PPrice getCurrentPrice();
    // Get Price by parking zone id
    PPrice getPriceByZoneId(int zoneId) throws DatabaseLayerException;
    
}
