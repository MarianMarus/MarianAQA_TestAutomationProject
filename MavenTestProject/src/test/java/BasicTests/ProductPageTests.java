package BasicTests;

import Pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ScreenshotUtil;

import java.util.List;

public class ProductPageTests extends BasePageTests{
    @Test(priority = 1 /*, groups = "SmokeTests"*/)
    public void checkButtonColor(){
        driver.get("http://sampleshop.inqa.pl/men/1-1-hummingbird-printed-t-shirt.html#/1-rozmiar-s/8-kolor-bialy");
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@data-button-action=\"add-to-cart\"]"));
        String buttonColor = addToCartButton.getCssValue("background-color");
        System.out.println(buttonColor);
        String buttonColorHex = Color.fromString(buttonColor).asHex();
        System.out.println(buttonColorHex);
        Assert.assertEquals(buttonColorHex, "#2fb5d2");
    }

    @Test(priority = 2 /*, groups = "SmokeTests"*/)
    public void checkAssuranceFont(){
        WebElement assuranceTextElement = driver.findElement(By.cssSelector(".blockreassurance_product .block-title"));
        String fontFamilyValue = assuranceTextElement.getCssValue("font-family");
        Assert.assertTrue(fontFamilyValue.startsWith("Noto Sans"));
    }

    @Test(priority = 3 /*, groups = "SmokeTests"*/)
    public void checkAttributeNameForQuantity(){
        WebElement quantityInput = driver.findElement(By.id("quantity_wanted"));
        String quantityName = quantityInput.getAttribute("name");
        System.out.println(quantityName);
        Assert.assertEquals(quantityName, "qty");
    }

    @Test(priority = 4 /*, groups = "SmokeTests"*/)
    public void checkColorSelection(){
        driver.get("http://sampleshop.inqa.pl/men/1-1-hummingbird-printed-t-shirt.html#/1-rozmiar-s/8-kolor-bialy");
        List<WebElement> colorInputElements = driver.findElements(By.cssSelector(".input-color"));

        System.out.println(colorInputElements.get(0).getAttribute("title"));
        Assert.assertTrue(colorInputElements.get(0).isSelected());
        Assert.assertFalse(colorInputElements.get(1).isSelected());
        //takeScreenshot("screenshot1");
        colorInputElements.get(1).click();
        Assert.assertTrue(colorInputElements.get(1).isSelected());
        Assert.assertFalse(colorInputElements.get(0).isSelected());
        //takeScreenshot("screenshot2");
    }

    @Test(priority = 5 /*, groups = "SmokeTests"*/)
    public void orderShirtAllProperties(){
        ProductPage productPage = new ProductPage(driver);
        productPage.openProductPage();
        ScreenshotUtil.takeScreenshot(driver,"allProp1");
        productPage.selectColor("black");
        productPage.selectSize("L");
        productPage.setNumberOfPieces("3");
        productPage.clickAddToBasketButton();
        ScreenshotUtil.takeScreenshot(driver, "allProp2");
        productPage.checkAddToBasketSummaryDisplayed();
    }

    @Test(enabled = false)
    public void orderShirtOnlySize(){
        ProductPage productPage = new ProductPage(driver);
        productPage.openProductPage();
        ScreenshotUtil.takeScreenshot(driver, "size1");
        productPage.selectSize("S");
        productPage.clickAddToBasketButton();
        ScreenshotUtil.takeScreenshot(driver, "size2");
    }

    @Test(dependsOnMethods = "orderShirtAllProperties")
    public void checkCartOverviewParameters(){
        ProductPage productPage = new ProductPage(driver);
        productPage.checkAddToBasketSummaryDisplayed();
        String color = productPage.getColorFromBasketSummary();
        String size = productPage.getSizeFromBasketSummary();
        String qty = productPage.getQuantityFromBasketSummary();
        Assert.assertEquals(color, "czarny");
        Assert.assertEquals(size, "L");
        Assert.assertEquals(qty, "2");
    }
}
