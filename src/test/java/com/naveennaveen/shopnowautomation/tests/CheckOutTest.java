package com.naveennaveen.shopnowautomation.tests;

import com.naveennaveen.shopnowautomation.base.BaseTest;

import com.naveennaveen.shopnowautomation.base.DriverManager;
import com.naveennaveen.shopnowautomation.pages.*;
import org.testng.annotations.Test;

import static com.naveennaveen.shopnowautomation.utils.UtilityClass.getProperty;

public class CheckOutTest extends BaseTest {


    @Test
    public void test1() {
        // Instantiate FlipkartHomePage object
        FlipkartHomePage homePage = new FlipkartHomePage(DriverManager.getDriver());

        // Instantiate ProductListingPage object
        ProductListingPage productListingPage = new ProductListingPage(DriverManager.getDriver());

        // Instantiate ProductDetailsPage object
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(DriverManager.getDriver());

        // Instantiate CartPage object
        CartPage cartPage = new CartPage(DriverManager.getDriver());

        CheckoutProcessPage checkoutProcessPage = new CheckoutProcessPage(DriverManager.getDriver());


        // Verify the page title of Flipkart home page
        homePage.verifyPageTitle();

        // Search for a product and select it on Flipkart home page
        homePage.searchAndSelectProduct("lenovo laptop");

        // Verify related products are displayed on the product listing page
        productListingPage.VerifyRelatedProductsDisplayed("lenovo");

        // Get details of the first product and click on it on the product listing page
        String firstProductDetails = productListingPage.getProductDetailsAndClick(1);

        // Verify the details of the selected product on the product details page
        productDetailsPage.verifyTheProduct();

        // Get the sale price of the product on the product details page
        String salePrice = productDetailsPage.getSalePrice();

        // Add the product to the cart on the product details page
        productDetailsPage.addToCart();

        // Click on the cart icon to proceed to checkout on the product listing page
        productListingPage.clickOnCartIcon();

        // Verify the product details and quantity on the cart page
        cartPage.verifyProductDetails(firstProductDetails, salePrice, "1");

        // Click on the "Place Order" button on the cart page
        cartPage.clickOnPlaceOrder();

        // Enter the mobile number and click continue on the checkout process page
        checkoutProcessPage.enterMobileNumberAndClickContinue(getProperty("mobileNumber"));

        // Enter the shipping address details on the checkout process page
        checkoutProcessPage.enterAddress(getProperty("name"), getProperty("mobileNumber"), getProperty("pingCode"), getProperty("locality"), getProperty("address"));

        // Verify the order summary with the provided details on the checkout process page
        checkoutProcessPage.verifyOrderSummary(salePrice, "1");


    }


}
