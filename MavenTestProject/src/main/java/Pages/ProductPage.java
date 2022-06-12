package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductPage extends BasePage{
    final static Logger LOGGER = LoggerFactory.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@title=\"Biały\"]")
    WebElement colorWhiteBox;
    @FindBy(xpath = "//input[@title=\"czarny\"]")
    WebElement colorBlackBox;
    @FindBy(id = "group_1")
    WebElement selectSizeElement;
    @FindBy(xpath = "//input[@name=\"qty\"]")
    WebElement numberOfPiecesInput;
    @FindBy(css = ".add button")
    WebElement addToBasketButton;

    @FindBy(css=".add button")
    WebElement addToBasketButtonElement;
    @FindBy(css = "#blockcart-modal")
    WebElement cartSummaryElement;
    By cartSummaryBy = By.cssSelector("#blockcart-modal");

    public void openProductPage(){
        LOGGER.info("Otwieram stronę produktu");
        driver.get("http://sampleshop.inqa.pl/men/1-1-hummingbird-printed-t-shirt.html#/1-rozmiar-s/8-kolor-bialy");
    }

    public void selectColor(String color){
        LOGGER.info("Wybieram kolor: " + color);
        if(color.equals("white")){
            colorWhiteBox.click();
        } else if (color.equals("black")) {
            colorBlackBox.click();
        }
    }

    public void selectSize(String size){
        Select selectSize = new Select(selectSizeElement);
        selectSize.selectByVisibleText(size);
    }

    public void setNumberOfPieces(String number){
        numberOfPiecesInput.click();
        numberOfPiecesInput.clear();
        numberOfPiecesInput.sendKeys(number);
    }

    public void clickAddToBasketButton(){
        addToBasketButtonElement.click();
    }

    public void checkAddToBasketSummaryDisplayed(){
        WebElement cartSummaryElement = waitForPresenceOfElement(cartSummaryBy);
        waitForElementVisibility(cartSummaryElement);
    }

    public String getColorFromBasketSummary(){
        return cartSummaryElement.findElement(By.cssSelector(".kolor strong")).getText();
    }

    public String getSizeFromBasketSummary(){
        return cartSummaryElement.findElement(By.cssSelector(".rozmiar strong")).getText();
    }

    public String getQuantityFromBasketSummary(){
        return cartSummaryElement.findElement(By.cssSelector(".product-quantity strong")).getText();
    }
}
