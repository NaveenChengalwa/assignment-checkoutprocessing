package com.naveennaveen.shopnowautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class BasePage {

    protected WebDriver driver;
    private static final int TIMEOUT_SECONDS = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to switch to a specific tab based on index
    public void switchToTab(int tabIndex) {
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(windowHandles);
        if (tabIndex >= 0 && tabIndex < windowHandles.size()) {
            driver.switchTo().window(windowHandlesList.get(tabIndex));
        } else {
            throw new IllegalArgumentException("Invalid tab index: " + tabIndex);
        }
    }

    // Method to switch to the last opened tab (assuming it's the newest tab)
    public void switchToNewTab() {
        Set<String> windowHandles = driver.getWindowHandles();
        String newTabHandle = windowHandles.stream().reduce((first, second) -> second).orElse(null);
        if (newTabHandle != null) {
            driver.switchTo().window(newTabHandle);
        } else {
            throw new IllegalStateException("No new tab found");
        }
    }

    // Method to close the currently focused tab
    public void closeCurrentTab() {
        driver.close();
    }


    // Method to wait for visibility of a single element
    public void waitForVisibilityOfElement(WebElement element) {
        waitForVisibilityOfElement(TIMEOUT_SECONDS, element);
    }

    // Method to wait for visibility of a single element with custom timeout
    public void waitForVisibilityOfElement(int timeoutInSeconds, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForPresenceOfElementLocated(By locator, int timeoutInSeconds ) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Method to wait for visibility of multiple elements
    public void waitForVisibilityOfElements(List<WebElement> elements) {
        waitForVisibilityOfElements(TIMEOUT_SECONDS, elements);
    }

    // Method to wait for visibility of multiple elements with custom timeout
    public void waitForVisibilityOfElements(int timeoutInSeconds, List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    // Other common methods shared across pages can be added here
}
