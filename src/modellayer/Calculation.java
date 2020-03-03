package modellayer;

import controllayer.IllegalCoinException;
import utility.Validation;

public class Calculation {
    private final modellayer.PPayStation PPayStation;

    public Calculation(modellayer.PPayStation PPayStation) {
        this.PPayStation = PPayStation;
    }

    public int getTimeBoughtInMinutes() {

        PPrice aPrice = PPayStation.getControlPrice().getCurrentPrice();
        int timeBoughtInMinutes = 0;

        double timeBoughtInSeconds = PPayStation.getAmount() * aPrice.getParkingPrice();
        timeBoughtInMinutes = (int) ((timeBoughtInSeconds + 59) / 60);

        return timeBoughtInMinutes;
    }

    public void validateCoin(Coin coin) throws IllegalCoinException {

        Validation.validateCoin(coin);
    }

    double getEuroCoinValueInCent(Coin coin) {
        double valueInCent = 0;
        double coinValue = coin.getAmount();

        if (coin.getCoinType() == Currency.ValidCoinType.INTEGER) {
            valueInCent = coinValue * 100;
        } else {
            valueInCent = coinValue;
        }

        return valueInCent;
    }

    double getDkkCoinValueInCent(Coin coin, PPrice price) {
        double valueInCent = 0;
        Currency.ValidCoinType coinType = coin.getCoinType();
        double coinValue = coin.getAmount();

        if (coinType == Currency.ValidCoinType.INTEGER) {
            valueInCent = (coinValue * 100) / price.getExchangeEuroDkk();
        } else {
            valueInCent = coinValue / price.getExchangeEuroDkk();
        }

        return valueInCent;
    }
}