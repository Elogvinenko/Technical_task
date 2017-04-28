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

    //Driver initialization, besed on system property
    WebDriver driver = Browsers.getDriver();

    //variables for scenario
    public String homeTeam;
    public String awayTeam;
    public double bet;

    @Given("^I open main page$")
    public void iOpenMainPage() {
        //new object with Page type creation
        MainPage mPage = new MainPage();
        //Driver sending and page initialization
        mPage.MainPageInit(driver);
        //Main page openning
        mPage.openBaseUrl();
    }

    @And("^I navigate to Football events$")
    public void iNavigateToFootballEvents() {
        //new object with Page type creation
        FootBallMatchesPage fPage = new FootBallMatchesPage();
        //Driver sending and page initialization
        fPage.FootBallPageInit(driver);
        fPage.FootBallMatchesPageInit(driver);
        //Football matches selection
        fPage.selectAllMatches();
    }

    //Getting 2 arguments from the gherkin scenario
    @When("^I select \"([^\"]*)\" vs \"([^\"]*)\" football match$")
    public void iSelectVsFootballMatch(String arg0, String arg1)  {
        //new object with Page type creation
        MatchPage fPage = new MatchPage();
        //Driver sending and page initialization
        fPage.MatchPageInit(driver);
        fPage.FootBallMatchesPageInit(driver);
        fPage.initializeTeams(arg0,arg1);
        //Teams saving for future usage
        homeTeam = arg0;
        awayTeam = arg1;
        //Match selection, based on team names
        fPage.selectMatch();
        //Home team win selection
        fPage.betOnHomeTeam();
    }

    //Getting double argument from gherkin scenario
    @And("^I fill (\\d+.\\d+) in bets$")
    public void iFillInBets(double arg0) {
        //new object with Page type creation
        BetsModule bets = new BetsModule();
        //Driver sending and page initialization
        bets.BetsModuleInit(driver);
        bets.initializeBet(arg0);
        //Bets save for future usage
        bet = arg0;
        //Placing bet into input field
        bets.placeBet();
    }

    @Then("^total stake is displayed$")
    public void totalStakeIsDisplayed() {
        //new object with Page type creation
        BetsModule bets = new BetsModule();
        //Driver sending and page initialization
        bets.BetsModuleInit(driver);
        //Waiting for some result
        bets.waitUntilTotalStakeIsVisible();
    }

    @And("^returns are correct$")
    public void returnsAreCorrect() {
        //new object with Page type creation
        BetsModule bets = new BetsModule();
        //Driver sending and page initialization
        bets.initializeBet(bet);
        bets.FootBallMatchesPageInit(driver);
        bets.initializeTeams(homeTeam,awayTeam);
        bets.BetsModuleInit(driver);
        //Checking, that displayed result is correct, based on calculated result
        Assert.assertEquals(Double.doubleToLongBits(bets.calculateCoefficient()), Double.doubleToLongBits(bets.getTotalReturn()));
    }
    @AfterSuite
    //Browser closing after the suite
    public void testDown(){
        driver.quit();
    }
}
