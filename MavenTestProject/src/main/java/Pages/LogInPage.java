package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogInPage extends BasePage{

    final static Logger LOGGER = LoggerFactory.getLogger(ProductPage.class);
    public LogInPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "form-control")
    WebElement loginField;

    @FindBy(xpath =
            "/html/body/main/section/div/div/section/section/section/form/section/div[2]/div[1]/div/input")
            //"form-control js-child-focus js-visible-password"
    WebElement passwordField;

    @FindBy(id = "submit-login")
    WebElement loginButton;

    @FindBy(linkText =
            "http://sampleshop.inqa.pl/odzyskiwanie-hasla")
    WebElement forgotPasswordLink;

    @FindBy(linkText =
            "http://sampleshop.inqa.pl/logowanie?create_account=1")
    WebElement registerLink;

    public void openLoginPage(){
        //LOGGER.info("Open Login Page");
        driver.get("http://sampleshop.inqa.pl/logowanie?back=my-account");
    }

    By headerLogInPage = By.xpath(
            "/html/body/main/section/div/div/section/header/h1");

    public String getTextOfHeaderLogInPage(){
        String actualHeader =driver.findElement(headerLogInPage).getText();
        return actualHeader;
    }

    public void enterLogin(String mail){
        fillElement(loginField, mail);
    }

    public void enterPassword(String pass){
        fillElement(passwordField, pass);
    }

    public void enterLogInButton(){
        clickElement(loginButton);
    }
}
