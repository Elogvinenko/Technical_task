package pages;

import config.Configuration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by elogvinenko on 26.04.17.
 */
public class MainPage {

    //@FindBy(xpath="//li[contains(@id,'nav-homepage')]/a")
    @FindBy(id = "header")
    WebElement homeButton;

    public static void jsClick(WebElement elem, WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", elem);

    }
    private WebDriver driver;

    public void MainPageInit(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openBaseUrl() {
        driver.navigate().to(Configuration.getBaseUrl());
        driver.manage().window().maximize();
        waitUntilMainPageOpened();
    }

    public void waitUntilMainPageOpened() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(homeButton));

    }
}
