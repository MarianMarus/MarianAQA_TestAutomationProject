package BasicTests;

import Pages.LogInPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Owner("QA MArian")
@Epic("Epic Name")
@Story("Story Name")
public class LogInPageTests extends BasePageTests {

    @Test(groups = "SmokeTests")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("http://google.com")
    public void goToLogInPageTest(){
        LogInPage logInPage = new LogInPage(driver);
        logInPage.openLoginPage();
        String actualHeaderText = logInPage.getTextOfHeaderLogInPage();
        Assert.assertEquals("Zaloguj się do swojego konta", actualHeaderText);
        logInPage.enterLogin("blablabla@gmail.com");
        logInPage.enterPassword("1234");
        logInPage.enterLogInButton();
    }
}
