package com.itinvolve.itsm.aplications;


import static com.itinvolve.itsm.framework.env.Setup.PATH_DRIVER_CHROME;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.itinvolve.itsm.framework.env.WebDriverHandler;
import com.itinvolve.itsm.framework.logs.Log;
import com.itinvolve.itsm.process.GoogleSearchPage;


public class Test2 {
//    @Test(groups = { "functest", "checkintest" })
//    public void testMethod1() {
//    }
//    @Test(groups = { "functest" })
//    public void testMethod2() {
//        boolean testBol = false;
//        Log.LOGGER.info("+++Test from thirth method");
//        Assert.assertEquals(testBol, true);
//    }

    @Test(groups = { "functest" })
    public void testMethod3() throws InterruptedException {
//        WebDriver driver = new FirefoxDriver();
//
//        // Navigate to the right place
//        driver.get("http://www.google.com/");
//
//        // Create a new instance of the search page class
//        // and initialise any WebElement fields in it.
//        GoogleSearchPage page = PageFactory.initElements(driver, GoogleSearchPage.class);
//
//        // And now do the search.
//        page.searchFor("Cheese");
//        driver.quit();


//        System.setProperty("webdriver.chrome.driver", PATH_DRIVER_CHROME);
//        WebDriver driver = new ChromeDriver();//FirefoxDriver();
//        driver.get("https://business.twitter.com/start-advertising");
//        System.out.println("Title1: " + driver.getTitle());
//        //Assert.assertEquals("Start Advertising | Twitter for Business", driver.getTitle());
//
//        // considering that there is only one tab opened in that point.
//        String oldTab = driver.getWindowHandle();
//        driver.findElement(By.linkText("Twitter Advertising Blog")).click();
//        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
//        System.out.println("Number Tabs " + newTab.size());
//        newTab.remove(oldTab);
//        // change focus to new tab
//        driver.switchTo().window(newTab.get(0));
//        System.out.println("Title2: " + driver.getTitle());
//        //Assert.assertEquals("Twitter Advertising", driver.getTitle());
//
//        // Do what you want here, you are in the new tab
//
//        driver.close();
//        // change focus back to old tab
//        driver.switchTo().window(oldTab);
//
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        System.out.println("Title3: " + driver.getTitle());
//        WebElement el = driver.findElement(By.xpath(".//*[@id='edit-search-block-form--2']"));
//        el.sendKeys("Test");
//        //Assert.assertEquals("Start Advertising | Twitter for Business", driver.getTitle());
//        //driver.findElement(By.linkText("Twitter Advertising Blog")).click();
//        driver.quit();

    }
}
