package com.epam.pages.verifications;

import com.epam.pages.actions.HubspotLoginPageEmail;
import org.openqa.selenium.WebDriver;

public class HubspotLoginPageEmailVerification extends HubspotLoginPageEmail {

    public HubspotLoginPageEmailVerification (WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyEmailFieldValue(String errMsg, String expValue, String ... formatArgs) {
        verifyElementValue(errMsg, getEmailField(formatArgs),expValue);
    }

    public void verifyEmailFieldPresence(String errMsg, boolean isPresent, String ... formatArgs) {
        verifyIsElementPresent(errMsg, isPresent, getEmailField(formatArgs), "EmailField");
    }

    public void verifyNextButtonText(String errMsg, String expectedValue, String... formatArgs) {
        verifyElementText(errMsg, getNextButton(formatArgs), expectedValue);
    }

}