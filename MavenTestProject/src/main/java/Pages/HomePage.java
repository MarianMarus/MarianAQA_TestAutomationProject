package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
        super(driver);
    }

    By firstProductPrice = By.cssSelector("article .price");
    //By getFirstProduct = By.cssSelector("article");

    public void openHomePage(){
        driver.get("http://sampleshop.inqa.pl/");
    }

    public String getFirstProductElementPrice(){
        String actualPrice = driver.findElement(firstProductPrice).getText();
        return actualPrice;
    }
    /*
    public void openFirstProduct(){
        driver.findElement(firstProductBy);
    }
    public void checkFirstProductIsDisplayed(){
        WebElement firstProduct = driver.findElement(firstProductBy);
    }*/
}
