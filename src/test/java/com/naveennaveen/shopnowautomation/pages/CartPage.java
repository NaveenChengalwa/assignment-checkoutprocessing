package com.naveennaveen.shopnowautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage extends BasePage {

  @FindBy(xpath = "//button//span[text()=\"Place Order\"]")
  private WebElement placeOrderButton;

  @FindBy(xpath ="//button//span[text()=\"Place Order\"]//ancestor::div[@class=\"_1AtVbE col-12-12 _78xt5Y\"]//preceding-sibling::div[1]")
  private WebElement productDetails;

  @FindBy(xpath="//div[text()=\"Total Amount\"]//parent::div//following-sibling::div")
  private WebElement totalAmount;

  @FindBy(xpath = "//div[contains(text(),\"Price\")]")
  private WebElement quantity;

    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyProductDetails(String productName, String amount, String noOfProducts){
      Assert.assertTrue(productDetails.getText().contains(productName.split("\n")[2].replace(".","")));
      Assert.assertEquals(totalAmount.getText(),amount);
      Assert.assertEquals(quantity.getText().split(" ")[1].replace("(",""), noOfProducts);
    }

    public void clickOnPlaceOrder(){
      Assert.assertTrue(placeOrderButton.isEnabled());
      placeOrderButton.click();
    }







}
