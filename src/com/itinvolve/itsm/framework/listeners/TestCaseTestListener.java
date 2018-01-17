package com.itinvolve.itsm.framework.listeners;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.itinvolve.itsm.framework.env.ScreenshotHandler;
import com.itinvolve.itsm.framework.env.Setup;
import com.itinvolve.itsm.framework.logs.Log;
import com.itinvolve.itsm.framework.logs.TestCaseLogger;
import com.itinvolve.itsm.framework.utils.TestngUtils;

public class TestCaseTestListener extends TestListenerAdapter  {
    @Override
    public void onTestFailure(ITestResult testResult) {
        Reporter.setCurrentTestResult(testResult);
        Log.LOGGER.error("-> The " + TestngUtils.getFullMethodName(testResult) + " test has FAILED because "  + testResult.getThrowable().getMessage());


        if (Setup.USE_BROWSER_FOR_TEST) {
            String fileName = TestngUtils.buildFileName(testResult);
            Reporter.log("<a href='logs/" + fileName + ".log'>Test Case Log</a>&nbsp &nbsp");
            Reporter.log("<a href='logs/catchAll.log'>General Log</a><br/>");

            ScreenshotHandler.takeScreenshotAddToReport(fileName, testResult);

            TestCaseLogger.logsToReport();

            ScreenshotHandler.compareImageFileToReport();
        }
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        Log.LOGGER.warn("-> The " + TestngUtils.getFullMethodName(testResult) + " test has SKIPPED");
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        Reporter.setCurrentTestResult(testResult);
        Log.LOGGER.info("-> The " + TestngUtils.getFullMethodName(testResult) + " test has PASSED");

        if (Setup.USE_BROWSER_FOR_TEST) {
            ScreenshotHandler.compareImageFileToReport();
        }
    }
}
