package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by elogvinenko on 26.04.17.
 */
public class Browsers {
    public static WebDriver getDriver(){
        WebDriver dr;
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        switch (System.getProperty("browser","chrome").toLowerCase()){
            case "chrome":
                dr =  new ChromeDriver();
                break;
            case "firefox":
                dr =  new FirefoxDriver();
                break;
            case "mobile":
                DesiredCapabilities  capabilities;
                String DeviceName;

                DeviceName = "Google Nexus 5";

                String ChromeDriverPath = System.getProperty("user.dir") + "/lib/chromedriver";
                System.setProperty("webdriver.chrome.driver", ChromeDriverPath);

                Map<String, String> mobileEmulation = new HashMap<String, String>();
                mobileEmulation.put("deviceName", DeviceName);

                Map<String, Object> chromeOptions = new HashMap<String, Object>();
                chromeOptions.put("mobileEmulation", mobileEmulation);

                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                dr = new ChromeDriver(capabilities);
                break;
            default:
                dr =  new ChromeDriver();
                break;
        }
        return dr;
    }
}
