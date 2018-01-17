/*
 * Copyright (c) ITinvolve, Inc. All Rights Reserved.
 */
package com.itinvolve.itsm.framework.logs;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;

/**
 * Test case logger class. Provide methods to write information in log files and in the test report
 * @author Adrian Espinoza
 *
 */
public class TestCaseLogger {
    /** Store the logs to write after */
    private static List<String> logs;

    /**
    * This method save log information and write in log files.
    * @param message The message to write in logs.
    */
    public static void log(String message) {
        if (null == logs) {
            logs = new ArrayList<String>();
        }
        logs.add(message);
        Log.LOGGER.info(message);
    }

    /**
     * This method adds the test cases steps into test report.
     */
    public static void logsToReport() {
        if ((null != logs) && !logs.isEmpty()) {
            String result = "";
            result += "<fieldset>";
            result += "<legend>Test Case Execution</legend>";
            result += "<ol style=\"padding-left: 3em;\">";
            for (String message : logs) {
                result += "<li>" + message +"</li>";
            }
            result += "</ol>";
            result += "</fieldset>";
            Reporter.log(result);
        }
    }

    /**
     * This method make a reset to log list information.
     */
    public static void reset() {
        logs = null;
    }
}
