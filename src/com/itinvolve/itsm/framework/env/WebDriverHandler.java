/*
 * Copyright (c) ITinvolve, Inc. All Rights Reserved.
 */
package com.itinvolve.itsm.framework.env;

import static com.itinvolve.itsm.framework.env.Setup.BROWSER;
import static com.itinvolve.itsm.framework.env.Setup.PATH_DRIVER_CHROME;
import static com.itinvolve.itsm.framework.env.Setup.PATH_DRIVER_IE;
import static com.itinvolve.itsm.framework.env.Setup.START_URL;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itinvolve.itsm.framework.logs.Log;
import com.opera.core.systems.OperaDriver;

/**
 * Web driver handler class. Provide methods to manage the browser window.
 * @author Adrian Espinoza
 *
 */
public class WebDriverHandler {
    /** Store the WebDriver */
    public static WebDriver driver;

    /** Store the WebDriverWait */
    public static WebDriverWait wait;

    /** Store the action events */
    public static Actions action;

    /** Default wait time for an element. 15  seconds. */
    public static final int DEFAULT_WAIT_ELEMENT = 20;

    /** Default wait time for a page to be displayed.  10 seconds. */
    public static final int DEFAULT_WAIT_PAGE = 25;

    /**
     * This method open the initial browser window according type browser configured in the file configuration.
     * @throws IOException
     */
    public static boolean openWindows(){
        return selectBrowser();
    }

    /**
     * This method closes the current focused window.
     */
    public static void closeWindows() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("-> After closing driver: " + driver);
        }
    }

    /**
     * This method allows navigate to start page.
     */
    public static void navigateToStartUrl() {
        driver.navigate().to(START_URL);
    }

    /**
     * This method clear all Internet cookies.
     */
    public static void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    /**
     * This method restart the web driver.
     */
    public static void restartWebDriver() {
        closeWindows();
        if (openWindows()) {
            navigateToStartUrl();
        }
    }

    /**
     * This method chooses a browser according parameter sent by command line.
     * @throws IOException
     */
    private static boolean selectBrowser() {
        boolean wasSelected = false;
        if (driver == null) {
            wasSelected = selectWebDriverNull();
        } else {
            wasSelected = selectWebDriverNotNull();
        }

        if (wasSelected) {
            System.out.println("Open WebDriver(" + BROWSER + ", " + driver.getClass().getCanonicalName() + ")");
            Log.LOGGER.info("-> Open WebDriver(" + BROWSER + ", " + driver.getClass().getCanonicalName() + ")");
            driver.manage().window().maximize();
            //driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_PAGE, TimeUnit.SECONDS);
            //this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

            wait = new WebDriverWait(driver, DEFAULT_WAIT_ELEMENT);
            action = new Actions(driver);
        } else {
            System.out.println("Error browser value not supported -> " + BROWSER + "  command line >ant runATC -Dbrowser=[chrome,firefox,ie,opera,safari]");
            Log.LOGGER.error("-> Error browser value not supported >>> " + BROWSER + "  command line >ant runATC -Dbrowser=[chrome,firefox,ie,opera,safari]");
        }
        return wasSelected;
    }

    /**
     * This method selects the web driver when this is not null.
     */
    private static boolean selectWebDriverNotNull() {
        boolean browserWasSelected = true;
        if (BROWSER.toLowerCase().equals(BrowserType.FIREFOX.getBrowser()) && !(driver instanceof FirefoxDriver)) {
            driver = new FirefoxDriver(generateDesiredCapabilities(BrowserType.FIREFOX));
        } else if (BROWSER.toLowerCase().equals(BrowserType.CHROME.getBrowser()) && !(driver instanceof ChromeDriver)) {
            System.setProperty("webdriver.chrome.driver", PATH_DRIVER_CHROME);
            driver = new ChromeDriver(generateDesiredCapabilities(BrowserType.CHROME));
        } else if (BROWSER.toLowerCase().equals(BrowserType.IE.getBrowser()) && !(driver instanceof InternetExplorerDriver)) {
            System.setProperty("webdriver.ie.driver", PATH_DRIVER_IE);
            driver = new InternetExplorerDriver(generateDesiredCapabilities(BrowserType.IE));
        } else if (BROWSER.toLowerCase().equals(BrowserType.OPERA.getBrowser()) && !(driver instanceof OperaDriver)) {
            driver = new OperaDriver(generateDesiredCapabilities(BrowserType.OPERA));
        } else if (BROWSER.toLowerCase().equals(BrowserType.SAFARI.getBrowser()) && !(driver instanceof SafariDriver)) {
            driver = new SafariDriver(generateDesiredCapabilities(BrowserType.SAFARI));
        } else {
            browserWasSelected = false;
        }
        return browserWasSelected;
    }

    /**
     * This method selects the web driver when this is null;
     */
    private static boolean selectWebDriverNull() {
        boolean browserWasSelected = true;
        if (BROWSER.toLowerCase().equals(BrowserType.FIREFOX.getBrowser())) {
            //driver = new FirefoxDriver(generateDesiredCapabilities(BrowserType.FIREFOX));
            driver = new FirefoxDriver();
        } else if (BROWSER.toLowerCase().equals(BrowserType.CHROME.getBrowser())) {
            System.setProperty("webdriver.chrome.driver", PATH_DRIVER_CHROME);
            driver = new ChromeDriver(generateDesiredCapabilities(BrowserType.CHROME));
        } else if (BROWSER.toLowerCase().equals(BrowserType.IE.getBrowser())) {
            System.setProperty("webdriver.ie.driver", PATH_DRIVER_IE);
            driver = new InternetExplorerDriver(generateDesiredCapabilities(BrowserType.IE));
        } else if (BROWSER.toLowerCase().equals(BrowserType.OPERA.getBrowser())) {
            driver = new OperaDriver(generateDesiredCapabilities(BrowserType.OPERA));
        } else if (BROWSER.toLowerCase().equals(BrowserType.SAFARI.getBrowser())) {
            driver = new SafariDriver(generateDesiredCapabilities(BrowserType.SAFARI));
        } else {
            browserWasSelected = false;
        }
        return browserWasSelected;
    }

    /**
     * This method configures the desired capabilities for a browser.
     * @param capabilityType The browser type.
     * @return A desired capabilities.
     */
    private static DesiredCapabilities generateDesiredCapabilities(BrowserType capabilityType) {
        DesiredCapabilities capabilities;

        switch (capabilityType) {
          case IE:
            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability("requireWindowFocus", true);
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            break;
          case SAFARI:
            capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("safari.cleanSession", true);
            break;
          case OPERA:
            capabilities = DesiredCapabilities.opera();
            capabilities.setCapability("opera.arguments", "-nowin -nomail");
            break;
          case CHROME:
            capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, String> chromePreferences = new HashMap<String, String>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            capabilities.setCapability("chrome.prefs", chromePreferences);
            break;
          case FIREFOX:
            capabilities = DesiredCapabilities.firefox();
            break;
          default:
            capabilities = DesiredCapabilities.htmlUnit();
            capabilities.setCapability("javascriptEnabled", "true");
        }

        return capabilities;
    }
}
