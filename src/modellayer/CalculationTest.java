package modellayer;

import controllayer.IllegalCoinException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculationTest {

    private PPayStation ps;
    private Calculation calculation;
    private Coin coin;

    @Before
    public void setUp() throws Exception {
        ps = new PPayStation(0,"Test");
        calculation = new Calculation(ps);
        coin = new Coin(0, Currency.ValidCurrency.EURO, Currency.ValidCoinType.INTEGER);
    }

    @After
    public void tearDown() throws Exception {
        ps = null;
        calculation = null;
    }

    @Test
    public void SettingTheAmountTo1ShouldReturn1Min() {
        //Arrange
        int expectedResult = 1;

        //Act
        ps.setAmount(1);

        //Assert
        assertEquals("Setting the amount to 1 should return 1 minute", expectedResult, calculation.getTimeBoughtInMinutes());
    }

    @Test
    public void SettingTheAmountTo1000ShouldReturn400Min() {
        //Arrange
        int expectedResult = 400;

        //Act
        ps.setAmount(1000);

        //Assert
        assertEquals("Setting the amount to 1000 should return 400 minute", expectedResult, calculation.getTimeBoughtInMinutes());
    }

    @Test
    public void Giving1EURCoinShouldntThrowException() {
        coin.setAmount(1);
        coin.setCurrency(Currency.ValidCurrency.EURO);
        coin.setCoinType(Currency.ValidCoinType.INTEGER);

        try {
            calculation.validateCoin(coin);
        } catch (IllegalCoinException e) {
            fail("Giving 1 EUR coin into the calculator should'n throw any exceptions");
        }
    }

    @Test(expected = IllegalCoinException.class)
    public void Giving7EURCoinShouldThrowException() throws IllegalCoinException {
        coin.setAmount(7);
        coin.setCurrency(Currency.ValidCurrency.EURO);
        coin.setCoinType(Currency.ValidCoinType.INTEGER);

        calculation.validateCoin(coin);
    }

    @Test
    public void EurInCent2EurShouldReturn200Cents() {
        coin.setAmount(2);
        coin.setCurrency(Currency.ValidCurrency.EURO);
        coin.setCoinType(Currency.ValidCoinType.INTEGER);
        assertEquals("A 2 EUR coin should equal 200 cents", 200, calculation.getEuroCoinValueInCent(coin),0);
    }

    @Test
    public void DkkInCent20KrShouldReturnCorrectCentValue() {
        coin.setAmount(20);
        coin.setCurrency(Currency.ValidCurrency.DKK);
        coin.setCoinType(Currency.ValidCoinType.INTEGER);
        PPrice price = new PPrice();
        price.setExchangeEuroDkk(7.5);

        double result = calculation.getDkkCoinValueInCent(coin, price);
        double expected = 20 / 7.5 * 100;

        assertEquals("20 Kr coin should give back a correct cent value",expected,result, 0.1);
    }
}