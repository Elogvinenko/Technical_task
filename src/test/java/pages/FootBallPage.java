package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by elogvinenko on 26.04.17.
 */
public class FootBallPage extends MainPage {

    @FindBy(id = "nav-football")
    WebElement footballButton;

    @FindBy(className = "link-viewall")
    WebElement showAllButton;

    private WebDriver driver;

    public void FootBallPageInit(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitUntilShowAllMatchesButtonVisible() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(showAllButton));
    }
}
