package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by elogvinenko on 27.04.17.
 */
public class FootBallMatchesPage extends FootBallPage {

    private String homeTeam;
    private String awayTeam;

    public String getHomeTeam() {
        return homeTeam;
    }

    @FindBy(id = "filterSelectionToday")
    WebElement todayFilterButton;

    private WebDriver driver;

    public void FootBallMatchesPageInit(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void initializeTeams(String firstTeam, String secondTeam) {
        homeTeam = firstTeam;
        awayTeam = secondTeam;
    }

    public void selectAllMatches() {
        footballButton.click();
        waitUntilShowAllMatchesButtonVisible();
        jsClick(showAllButton,driver);
        //showAllButton.click();
        waitUntilTodayMatchesButtonVisible();
    }

    public void waitUntilTodayMatchesButtonVisible() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(todayFilterButton));
    }

    public void selectMatch() {
        WebElement match = driver.findElement(By.xpath("//a[contains(@title,'"+homeTeam+" vs "+awayTeam+"')]"));
        match.click();
    }
}
