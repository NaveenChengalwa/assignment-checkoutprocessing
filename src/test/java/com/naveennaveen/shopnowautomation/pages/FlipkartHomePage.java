package com.naveennaveen.shopnowautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class FlipkartHomePage extends BasePage {
    @FindBy(css= "input[type='text'][title*='Search for']")
    private WebElement productSearchTextBox;

    @FindBy(xpath = "(//div[@id=\"container\"]//descendant::picture)[1]")
    private WebElement flipkartTitle;

    @FindBy(xpath = "//input[@type='text']/ancestor::form/descendant::ul/li//descendant::span")
    private List<WebElement> firstResult;

    public FlipkartHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyPageTitle(){
        String title = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
        Assert.assertEquals(driver.getTitle(),  title);
        Assert.assertTrue(flipkartTitle.getAttribute("title").contains("Flipkart"));
    }

    private int findElementIndexWithText(String text, List<WebElement> elements) {
        for (int i = 0; i < elements.size(); i++) {
            WebElement product = elements.get(i);
            String productText = product.getText();
            if (productText.contains(text)) {
                return i; // Return the index if the text contains the specified string
            }
        }
        return -1;
    }
    public void searchAndSelectProduct(String ProductName){
        productSearchTextBox.sendKeys(ProductName);
        waitForVisibilityOfElements(2000, firstResult);
       int index = findElementIndexWithText(ProductName,firstResult);
        firstResult.get(index).click();
    }

}
