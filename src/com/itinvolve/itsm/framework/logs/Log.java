/*
 * Copyright (c) ITinvolve, Inc. All Rights Reserved.
 */
package com.itinvolve.itsm.framework.logs;

import org.apache.log4j.Logger;


/**
 * Log class. Provide an attribute to write information into file logs using Log4J library.
 * @author Adrian Espinoza
 *
 */
public class Log {
    /** Store the logger instance */
    public static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
        if (!(Log4jPropertiesHandler.log4jPropertiesWasLoaded())) {
            Log4jPropertiesHandler.setUp();
        }
    }
}