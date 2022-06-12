package BasicTests;

import Pages.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BasePageTests{
    @Test//(groups = {"SmokeTests"})
    public void checkHomePageTitle(){
        driver.get("http://sampleshop.inqa.pl/");
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, "Automation Sample Shop");
    }

    @Test//(groups = {"SmokeTests"})
    public void checkProductPrice(){
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        String actualPrice = homePage.getFirstProductElementPrice();
        Assert.assertEquals("23,52 zł", actualPrice);
    }
}
