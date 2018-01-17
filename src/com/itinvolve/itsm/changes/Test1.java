package com.itinvolve.itsm.changes;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.itinvolve.itsm.framework.annotations.BeforeTestMethod;
import com.itinvolve.itsm.framework.logs.Log;

public class Test1 {
    @BeforeTestMethod(methods = { "testMethod1", "testMethod2" })
    public void btmCustomAnnotation() {
        Log.LOGGER.info("-> Custom annotation @BeforeTestMethod");
    }

    @Test(groups = { "functest", "checkintest" })
    public void testMethod1() {
        boolean testBol = false;
        Log.LOGGER.info("-> Test from first method");
        Assert.assertEquals(testBol, true);
    }

    @Test(groups = {"functest", "checkintest"} )
    public void testMethod2() {
        boolean testBol = false;
        Log.LOGGER.info("-> Test from second method");
        Assert.assertEquals(testBol, true);
    }

    @Test(groups = { "functest" })
    public void testMethod3() {
        boolean testBol = false;
        Log.LOGGER.info("-> Test from thirth method");
        Assert.assertEquals(testBol, true);
    }
}
