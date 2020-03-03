package modellayer;

import controllayer.ControlPrice;
import controllayer.IllegalCoinException;

/**
 * Inspired by the book: Flexible, Reliable Software
 * Henrik Bærbak Christensen: Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class PPayStation {

	private final Calculation calculation = new Calculation(this);
	// PayStation ident
	private int id;	
	// PayStaion model
	private String payStationModel;
	// Amount inserted in pay station
	private double amount = 0;
	private ControlPrice controlPrice;
	
	
	public PPayStation(int id, String payStationModel) {
		this.id = id;
		this.payStationModel = payStationModel;
		
		this.controlPrice = new ControlPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPayStationModel() {
		return payStationModel;
	}

	public void setPayStationModel(String payStationModel) {
		this.payStationModel = payStationModel;
	}	
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ControlPrice getControlPrice() {
		return controlPrice;
	}

	public void setControlPrice(ControlPrice controlPrice) {
		this.controlPrice = controlPrice;
	}

	public void addAmount(Coin coin, PPrice currentPrice) {
		
		Currency.ValidCurrency currency = coin.getCurrency();
		
		double valueInCent = 0;

		if (currency == Currency.ValidCurrency.DKK) {
			//PPrice nowPrice = controlPrice.getCurrentPrice();
			valueInCent = calculation.getDkkCoinValueInCent(coin, currentPrice);
		} else {
			valueInCent = calculation.getEuroCoinValueInCent(coin);
		}
		
		this.amount += valueInCent;
	}
	
	public int getTimeBoughtInMinutes() {

		return calculation.getTimeBoughtInMinutes();
	}
	
	public void validateCoin(Coin coin) throws IllegalCoinException {

		calculation.validateCoin(coin);
	}
	

	private double getEuroCoinValueInCent(Coin coin) {

		return calculation.getEuroCoinValueInCent(coin);
	}

	private double getDkkCoinValueInCent(Coin coin, PPrice price) {

		return calculation.getDkkCoinValueInCent(coin, price);
	}
	
}
