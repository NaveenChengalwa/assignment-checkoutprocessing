package com.naveennaveen.shopnowautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends BasePage {

    @FindBy(xpath = "//button[text()=\"Add to cart\"]")
    private WebElement addToCartButton;

    @FindBy(xpath ="//h1//span")
    private WebElement productName;


    @FindBy(xpath="(//span[text()=\"Special price\"]//parent::div//following-sibling::div//descendant::div)[3]")
    private WebElement salePrice;
    // Constructor
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Method to add the product to the shopping cart
    public void addToCart() {
        addToCartButton.click();
        closeCurrentTab();
    }

    public void verifyTheProduct(){
        switchToNewTab();
        waitForPresenceOfElementLocated(By.xpath("//h1//span"), 60);
        String productHeading = driver.findElement(By.xpath("//h1//span")).getText();
    }

    public String getSalePrice(){
      return  salePrice.getText();
    }







}
