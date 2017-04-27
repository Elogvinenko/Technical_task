package steps;


import config.Browsers;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import pages.BetsModule;
import pages.FootBallMatchesPage;
import pages.MainPage;
import pages.MatchPage;


/**
 * Created by elogvinenko on 26.04.17.
 */
public class PlaceBet {


    WebDriver driver = Browsers.getDriver();

    public String homeTeam;
    public String awayTeam;
    public double bet;

    @Given("^I open main page$")
    public void iOpenMainPage() throws Throwable {
        MainPage mPage = new MainPage();
        mPage.MainPageInit(driver);
        mPage.openBaseUrl();
    }

    @And("^I navigate to Football events$")
    public void iNavigateToFootballEvents() throws Throwable {
        FootBallMatchesPage fPage = new FootBallMatchesPage();
        fPage.FootBallPageInit(driver);
        fPage.FootBallMatchesPageInit(driver);
        fPage.selectAllMatches();
    }

    @When("^I select \"([^\"]*)\" vs \"([^\"]*)\" football match$")
    public void iSelectVsFootballMatch(String arg0, String arg1) throws Throwable {
        MatchPage fPage = new MatchPage();
        fPage.MatchPageInit(driver);
        fPage.FootBallMatchesPageInit(driver);
        fPage.initializeTeams(arg0,arg1);
        homeTeam = arg0;
        awayTeam = arg1;
        fPage.selectMatch();
        fPage.betOnHomeTeam();
    }

    @And("^I fill (\\d+.\\d+) in bets$")
    public void iFillInBets(double arg0) throws Throwable {
        BetsModule bets = new BetsModule();
        bets.BetsModuleInit(driver);
        bets.initializeBet(arg0);
        bet = arg0;
        bets.placeBet();
    }

    @Then("^total stake is displayed$")
    public void totalStakeIsDisplayed() throws Throwable {
        BetsModule bets = new BetsModule();
        bets.BetsModuleInit(driver);
        bets.waitUntilTotalStakeIsVisible();
    }

    @And("^returns are correct$")
    public void returnsAreCorrect() throws Throwable {
        BetsModule bets = new BetsModule();
        bets.initializeBet(bet);
        bets.FootBallMatchesPageInit(driver);
        bets.initializeTeams(homeTeam,awayTeam);
        bets.BetsModuleInit(driver);
        Assert.assertEquals(Double.doubleToLongBits(bets.calculateCoefficient()), Double.doubleToLongBits(bets.getTotalReturn()));
        driver.quit();
    }
    @AfterSuite
    public void testDown(){
        driver.quit();
    }
}
