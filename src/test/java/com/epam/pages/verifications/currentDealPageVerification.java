package com.epam.pages.verifications;

import com.epam.pages.actions.currentDealPage;
import org.openqa.selenium.WebDriver;

public class currentDealPageVerification extends currentDealPage {

    public currentDealPageVerification (WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyActivitiesText(String errMsg, String expectedValue, String... formatArgs) {
        verifyElementText(errMsg, getActivities(formatArgs), expectedValue);
    }

}