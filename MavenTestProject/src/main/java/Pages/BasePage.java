package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    protected BasePage(WebDriver driver) {this.driver = driver;}
    public void waitForElementVisibility(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForPresenceOfElement(By elementLocator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    public void clickElement(WebElement element) {
        element.click();
    }
    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }
    public String getTextFromElement(WebElement element) {
        return element.getText();
    }
    public void fillElement(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
