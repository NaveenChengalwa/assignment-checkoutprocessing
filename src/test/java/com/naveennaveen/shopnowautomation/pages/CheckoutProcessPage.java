package com.naveennaveen.shopnowautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CheckoutProcessPage extends BasePage {
    @FindBy(xpath = "//span[contains(text(),\"Email/Mobile\")]/../../child::input")
    private WebElement emailOrMobileTextBox;

    @FindBy(xpath = "//span[text()=\"CONTINUE\"]")
    private WebElement continueButton;

    @FindBy(xpath = "//*[contains(text(),\"address\")]")
    private WebElement addressIcon;

    @FindBy(xpath = "input[name=\"name\"]")
    private WebElement nameTextBox;

    @FindBy(xpath = "input[name=\"phone\"]")
    private WebElement phoneTextBox;

    @FindBy(xpath = "label[text()=\"Pincode\"]")
    private WebElement pinCodeTextBox;

    @FindBy(xpath = "label[text()=\"Locality\"]")
    private WebElement localityTextBox;

    @FindBy(xpath = "//label[contains(text(),\"Address\")]")
    private WebElement addressTextBox;

    @FindBy(xpath = "//button[contains(text(),\"Deliver\")]")
    private WebElement deliverButton;

    @FindBy(xpath = "//div[contains(text(),\"Price (\")]")
    private WebElement productQuantity;

    @FindBy(xpath = "//div[contains(text(),\"Total Payable\")]/..//following-sibling::div")
    private WebElement totalPayableAmount;

    public CheckoutProcessPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterMobileNumberAndClickContinue(String mobileNumber) {
        emailOrMobileTextBox.click();
        emailOrMobileTextBox.sendKeys(mobileNumber);
        continueButton.click();
    }

    public void enterAddress(String name, String mobile, String pinCode, String locality, String Address) {
        addressIcon.click();
        nameTextBox.sendKeys(name);
        phoneTextBox.sendKeys(mobile);
        pinCodeTextBox.sendKeys(pinCode);
        localityTextBox.sendKeys(locality);
        addressTextBox.sendKeys(Address);
        deliverButton.click();
    }

    public void verifyOrderSummary(String productPrice, String quantity) {
        Assert.assertEquals(totalPayableAmount.getText(), productPrice);
        Assert.assertEquals(productQuantity.getText(), quantity);
    }


}
