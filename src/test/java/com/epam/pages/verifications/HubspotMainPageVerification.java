package com.epam.pages.verifications;

import com.epam.pages.actions.HubspotMainPage;
import org.openqa.selenium.WebDriver;

public class HubspotMainPageVerification extends HubspotMainPage {

    public HubspotMainPageVerification (WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyCrmText(String errMsg, String expectedValue, String... formatArgs) {
        verifyElementText(errMsg, getCrm(formatArgs), expectedValue);
    }

    public void verifyCrmPresence(String errMsg, boolean isPresent, String ... formatArgs) {
        verifyIsElementPresent(errMsg, isPresent, getCrm(formatArgs), "Crm");
    }

    public void verifyDealsText(String errMsg, String expectedValue, String... formatArgs) {
        verifyElementText(errMsg, getDeals(formatArgs), expectedValue);
    }

    public void verifyDealsPresence(String errMsg, boolean isPresent, String ... formatArgs) {
        verifyIsElementPresent(errMsg, isPresent, getDeals(formatArgs), "Deals");
    }

}