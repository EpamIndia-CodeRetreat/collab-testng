package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;
public class HubspotLandingPage extends PageWrapper {

    private static final String acceptCookies = "xpath -> //button[@id='hs-eu-confirmation-button']";
    private static final String login = "xpath -> //ul[@class='hsg-nav__group']//a[contains(text(),'Log in')]";

    public HubspotLandingPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getAcceptCookies(String ... formatArgs) {
        return formatLocator(acceptCookies, formatArgs);
    }

    public String getLogin(String ... formatArgs) {
        return formatLocator(login, formatArgs);
    }

    public void clickAcceptCookies(String... formatArgs){
        click(acceptCookies, "acceptCookies",formatArgs);
    }

    public void clickLogin(String... formatArgs){
        click(login, "login",formatArgs);
    }

}
