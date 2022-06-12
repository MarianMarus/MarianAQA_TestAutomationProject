package BasicTests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import utils.DriverManager;
import utils.FileUtils;
import utils.PropertiesReader;
import utils.ScreenshotUtil;

import java.text.SimpleDateFormat;
import java.util.Properties;

public class BasePageTests {
    final static Logger LOGGER = LoggerFactory.getLogger(BasePageTests.class);
    public Properties properties;
    public WebDriver driver;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(String browser){
        LOGGER.info("Run SetUp method for class");
        driver = new DriverManager().getDriver(browser);
        properties = new PropertiesReader().getConfigurationProperties(FileUtils.getResourceFilePath
                ("config.properties"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        LOGGER.info("Run method TearDown for class ");
        driver.quit();
    }

    @BeforeMethod
    public void methodSetUp(ITestResult result){
        LOGGER.info("Run test method " + result.getMethod().getMethodName());
    }

    @AfterMethod
    public void methodTearDown(ITestResult result){
            String methodName = result.getMethod().getMethodName();
            SimpleDateFormat sdf = new SimpleDateFormat(properties.getProperty("dateTimeFormat"));
            long timestampMillis = result.getEndMillis();
            String timestamp = sdf.format(timestampMillis);
            String fileName = methodName + "_" + timestamp;
            if (result.getStatus() != ITestResult.SUCCESS) {
                ScreenshotUtil.takeScreenshot(driver, fileName);
            }
        LOGGER.info("Finishing test method " + methodName);
    }
}
