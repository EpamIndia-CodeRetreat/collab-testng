package com.epam.pages.verifications;

import com.epam.pages.actions.DealsInHubspot;
import org.openqa.selenium.WebDriver;

public class DealsInHubspotVerification extends DealsInHubspot {

    public DealsInHubspotVerification (WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyCreateDealText(String errMsg, String expectedValue, String... formatArgs) {
        verifyElementText(errMsg, getCreateDeal(formatArgs), expectedValue);
    }

    public void verifycreateDealButtonPresence(String errMsg, boolean isPresent, String ... formatArgs) {
        verifyIsElementPresent(errMsg, isPresent, getcreateDealButton(formatArgs), "createDealButton");
    }

}