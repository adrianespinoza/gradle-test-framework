package com.itinvolve.itsm.common.testcases;

import org.testng.annotations.Test;

import com.itinvolve.itsm.change.handlers.ITinvolveChangesElementPage;
import com.itinvolve.itsm.change.handlers.ITinvolveChangesPage;
import com.itinvolve.itsm.common.handlers.ITinvolveAppPage;
import com.itinvolve.itsm.common.handlers.ITinvolveComputerWindowPage;
import com.itinvolve.itsm.common.handlers.ITinvolveCreateObjectsIFrame;
import com.itinvolve.itsm.common.handlers.ITinvolveMainBarPageComponent;
import com.itinvolve.itsm.common.handlers.SalesforceMainBarPageComponent;
import com.itinvolve.itsm.common.handlers.XpressMainBarPageComponent;
import com.itinvolve.itsm.framework.sfusers.Profile;
import com.itinvolve.itsm.framework.utils.HandlerFactory;
import com.itinvolve.itsm.framework.utils.WebUtils;

public class TempTest {
    @Test(testName="ITI-20473", groups = {"Win"}, description = "put test description here")
    public void goToIFrameTest() {
        XpressMainBarPageComponent xMainBarComp = HandlerFactory.getInstance(XpressMainBarPageComponent.class);
        xMainBarComp.goToLoginPage();
    }
}

