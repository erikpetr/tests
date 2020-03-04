package databaselayer;

import modellayer.PBuy;

public interface IDbPBuy {

    // insert new PBuy
    int insertParkingBuy(PBuy parkingBuy) throws DatabaseLayerException;
 		
 	// Delete PBuy
    int deleteParkingBuy(PBuy parkingBuy) throws DatabaseLayerException;
    
}
