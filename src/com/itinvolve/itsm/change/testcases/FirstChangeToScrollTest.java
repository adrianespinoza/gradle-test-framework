package com.itinvolve.itsm.change.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.itinvolve.itsm.change.handlers.ITinvolveChangesElementPage;
import com.itinvolve.itsm.change.handlers.ITinvolveChangesPage;
import com.itinvolve.itsm.common.handlers.ITinvolveAppPage;
import com.itinvolve.itsm.common.handlers.ITinvolveMainBarPageComponent;
import com.itinvolve.itsm.common.handlers.SalesforceMainBarPageComponent;
import com.itinvolve.itsm.framework.sfusers.Profile;
import com.itinvolve.itsm.framework.utils.HandlerFactory;
import com.itinvolve.itsm.framework.utils.WebUtils;

public class FirstChangeToScrollTest {
    @Test(testName="ITI-20473", groups = {"Changes"}, priority = 2, description = "put test description here")
    public void testVerifyNewStatus() throws Exception {
        SalesforceMainBarPageComponent sMainBarComp = HandlerFactory.getInstance(SalesforceMainBarPageComponent.class);
        sMainBarComp.switchUser(Profile.SYSADMIN);//its convenient return SalesforceHomePage

        ITinvolveAppPage itAppPage = sMainBarComp.goToITinvolveAppPage();
        itAppPage.goToITinvolveMainAppPage();//return ITinvolveHomePage
        WebUtils.waitForPageFullyLoaded();

        ITinvolveMainBarPageComponent itMainBarComp = HandlerFactory.getInstance(ITinvolveMainBarPageComponent.class);
        ITinvolveChangesPage itChangesPage = itMainBarComp.goToBrowseAllChangesPage();
        WebUtils.waitForPageFullyLoaded();
        ITinvolveChangesElementPage itChangeElementPage = itChangesPage.selectFirstChangeElement();
        WebUtils.waitForPageFullyLoaded();
        //WebUtils.waitForPageLoadTimeout(10);
//
//        //WebUtils.waitForNSeconds(5);
////        WebUtils.windowScrollUp();
////        WebUtils.waitForNSeconds(5);
//
//        WebElement ele = WebUtils.waitForElement(By.xpath(".//*[@id='j_id0:j_id2:SiteTemplate:j_id333:viewEditChange:editChangeForm:readMode:ViewEditChangeViewButtonsTop:ViewEditChangeViewEditButtonTop']"));
//        WebUtils.windowScrollDown();
//        WebUtils.scrollIntoViewHeaderOffset(ele);
//        WebUtils.clickOn(ele);
//        WebUtils.waitForNSeconds(5);
    }
}
