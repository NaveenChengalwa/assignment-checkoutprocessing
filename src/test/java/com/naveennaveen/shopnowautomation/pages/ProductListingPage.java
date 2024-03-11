package com.naveennaveen.shopnowautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ProductListingPage extends BasePage {

    @FindBy(xpath = "//div[@id=\"container\"]//descendant::div[@class=\"_1AtVbE col-12-12\"]//descendant::a[@target=\"_blank\"]")
    private List<WebElement> relatedProductsSection;

    @FindBy(xpath = "//span[text()=\"Cart\"]")
    private WebElement cartIcon;


    // Method to check if related products section is displayed
    public ProductListingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void VerifyRelatedProductsDisplayed(String relatedProductName) {
        waitForVisibilityOfElements(relatedProductsSection);
        List<WebElement> relatedProductsSectionList = relatedProductsSection;
        for(int i = 0; i < 10; i++){
            String productName  = relatedProductsSectionList.get(i).getText();
            Assert.assertTrue(productName.toLowerCase().contains(relatedProductName.toLowerCase()));
        }
    }

    public String getProductDetailsAndClick(int index) {
        WebElement product = relatedProductsSection.get(index);
        String productDetail = product.getText();
        product.click();
        return productDetail;
    }


    public void clickOnCartIcon(){
        switchToTab(0);
        cartIcon.click();
    }
}
