package databaselayer;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import modellayer.*;

public class DatabasePPrice implements IDbPPrice {
	
	//Hardcoded for now. TODO: Use database
	public PPrice getCurrentPrice() {
		return new PPrice();
	}
	
	public PPrice getPriceByZoneId(int zoneId) throws DatabaseLayerException {
		PPrice foundPrice;
		
		Connection con = DBConnection.getInstance().getDBcon();

		String baseSelect = "SELECT price ";
		baseSelect += " FROM PPrice ";
		baseSelect += " WHERE pZone_id = " + zoneId;
		baseSelect += " AND starttime IN (";
		baseSelect += "	 SELECT MAX(starttime)";
		baseSelect += "  FROM PPrice";
		baseSelect += "  WHERE pZone_id = " + zoneId;
		baseSelect += " );";
		System.out.println(baseSelect);
	
		// ResultSet rs = null; 
		int price;
		PZone pZone; 
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			System.out.println("1");
			
			ResultSet rs = stmt.executeQuery(baseSelect);

			System.out.println(rs == null);
			rs.next();
			price = rs.getInt(1);

			pZone = new PZone(zoneId, "TestName");
			foundPrice = new PPrice(price, pZone);

			stmt.close();
		} catch (SQLException ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Error retrieving data");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (NullPointerException ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Null pointer exception - possibly Connection object");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (Exception ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Data not retrieved! Technical error");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} finally {
			DBConnection.closeConnection();
		}
				
		return foundPrice;
	}
	

}
