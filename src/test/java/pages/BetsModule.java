package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;

/**
 * Created by elogvinenko on 27.04.17.
 */
public class BetsModule extends MatchPage {

    private double bet = 0;
    private WebDriver driver;

    @FindBy (css = ".betslip-selection__stake-container.betslip-selection__stake-container--single input")
    WebElement betInput;

    @FindBy (id = "total-to-return-price")
    WebElement totalReturn;

    @FindBy (id = "total-stake-price")
    WebElement totalStake;

    //Page objects initialization
    public void BetsModuleInit(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Bet's value saving
    public void initializeBet(double amount) {
        bet = amount;
    }

    //Result calculated by site
    public double getTotalReturn() {
        double a = Double.parseDouble(totalReturn.getText());
        return a;
    }

    //Bet's coefficient getter
    public double[] getCoefficient() {
        String teamName = getHomeTeam();
        WebElement homeWin = driver.findElement(By.xpath("//button[contains(@data-player,'"+teamName+"')]"));
        //Values are taken in string, so they're needed to be converted
        double a = Integer.parseInt(homeWin.getAttribute("data-num"));
        double b = Integer.parseInt(homeWin.getAttribute("data-denom"));
        return new double[] {a, b};
    }

    //Placing bet into field
    public void placeBet() {
        waitUntilBetsInputVisible();
        betInput.sendKeys(String.valueOf(bet));
    }

    //Result calculations
    public double calculateCoefficient() {
        double coefficient[] = getCoefficient();
        //Formula for British bets
        double result = bet * (coefficient[0] / coefficient[1] + 1);
        //Decimal part should be decreased, and by the rules of bets - it's rounded to lower value
        result = BigDecimal.valueOf(result).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        return result;
    }

    //waiter for result
    public void waitUntilTotalStakeIsVisible() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(totalStake));
    }

    //waiter for bet's input
    public void waitUntilBetsInputVisible() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(betInput));
    }

}
