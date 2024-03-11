package com.naveennaveen.shopnowautomation.base;

import org.openqa.selenium.WebDriver;

/**
 * DriverManager class manages WebDriver instances using ThreadLocal.
 * ThreadLocal ensures thread safety by providing separate instances of WebDriver for each thread.
 */
public class DriverManager {

    // ThreadLocal variable to store WebDriver instances
    private final static  ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();

    /**
     * Retrieves the WebDriver instance associated with the current thread.
     * @return WebDriver instance associated with the current thread
     */
    public static WebDriver getDriver(){
        return threadLocal.get();
    }

    /**
     * Sets the WebDriver instance for the current thread.
     * @param driver WebDriver instance to be set
     */
    public static void setDriver(WebDriver driver){
        threadLocal.set(driver);
    }

    /**
     * Quits the WebDriver instance associated with the current thread.
     * Removes the WebDriver instance from the ThreadLocal variable.
     */
    public static void quitDriver(){
        WebDriver driver = getDriver();
        if(driver != null){
            driver.quit();
            threadLocal.remove();
        }
    }
}