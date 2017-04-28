package config;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by elogvinenko on 27.04.17.
 */
//Report and feature for run configuration
public class Runner {
    @CucumberOptions(features = "src/test/resources/features/place_bet.feature",
            format = {"pretty", "html:report/Place-bet-html-report", "json:report/Login-IdeaBox-report.json"},
            glue = "steps")
    public class FeatureRunner extends AbstractTestNGCucumberTests {
    }
}
