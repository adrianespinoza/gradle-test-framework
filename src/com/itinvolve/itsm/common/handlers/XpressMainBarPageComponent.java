package com.itinvolve.itsm.common.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.itinvolve.itsm.framework.utils.WebUtils;

public class XpressMainBarPageComponent {
    private By singInButtonLocator = By.cssSelector("li>a[class^='button ghost radius']");
    private By topNavSectionLocator = By.cssSelector("top-nav>nav[class^='top-landing-bar']");
    private By test = By.cssSelector("div>a[class^='login-input sign-in-button']");
    public void goToLoginPage() {
        WebElement userNavElement = WebUtils.waitForElement(topNavSectionLocator);
        if(userNavElement != null) {
            WebElement singInButton = WebUtils.waitForElement(singInButtonLocator);
            if (singInButton != null) {
                singInButton.click();
            }
            WebElement singInButton2 = WebUtils.waitForElement(test);
        }
    }
}
