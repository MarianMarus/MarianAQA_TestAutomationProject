package utils;

import BasicTests.BasePageTests;
import Pages.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    static WebDriver driver;

    public WebDriver getDriver(String browser) {

        LOGGER.info("Wybrany driver to " + browser.toUpperCase());

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(setFirefoxOptions());
        }

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    private FirefoxOptions setFirefoxOptions() {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");
        return options;
    }
}
