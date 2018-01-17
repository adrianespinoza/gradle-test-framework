package com.itinvolve.itsm.framework.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.itinvolve.itsm.framework.env.Setup;
import com.itinvolve.itsm.framework.env.WebDriverHandler;
import com.itinvolve.itsm.framework.logs.Log;
import com.itinvolve.itsm.framework.logs.Log4jPropertiesHandler;
import com.itinvolve.itsm.framework.utils.HandlerFactory;


public class TestCaseSuiteListener implements ISuiteListener {

    @Override
    public void onFinish(ISuite arg0) {
        Log4jPropertiesHandler.swithTestCaseLogFileName("catchAllTC");

        if (Setup.USE_BROWSER_FOR_TEST) {
            WebDriverHandler.closeWindows();
            Log.LOGGER.info("-> The browser was CLOSED");
            System.out.println("The browser was closed at the end suite...");
        }
    }

    @Override
    public void onStart(ISuite arg0) {
        Log4jPropertiesHandler.setUp();

        if (Setup.USE_BROWSER_FOR_TEST) {
            Log.LOGGER.info("-> The tests will be execute in " + Setup.BROWSER + " browser");
            boolean wasOpen = WebDriverHandler.openWindows();
            Log.LOGGER.info("-> The " + Setup.BROWSER + " browser was OPENED");

            if (wasOpen) {
                boolean wasSetup = Setup.setupEnvironment();//load user and configure the environment, urls auth end point
                Log.LOGGER.info("-> The environment was CONFIGURED");

                if (wasSetup) {
                    WebDriverHandler.navigateToStartUrl();
                    Log.LOGGER.info("-> The " + Setup.START_URL + " page has been OPEN");

                    HandlerFactory.clear();//clear the factory cache by suite
                }
                System.out.println("The browser was opened at the start suite...");
            }
        }
    }

}
