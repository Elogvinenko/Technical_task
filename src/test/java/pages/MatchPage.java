package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by elogvinenko on 27.04.17.
 */
public class MatchPage extends FootBallMatchesPage {


    private WebDriver driver;


    public void MatchPageInit(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void betOnHomeTeam() {
        String teamName = getHomeTeam();

        WebElement homeWin = driver.findElement(By.xpath("//button[contains(@data-player,'"+teamName+"')]"));
        jsClick(homeWin, driver);
    }




}
