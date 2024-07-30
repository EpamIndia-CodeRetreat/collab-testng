package com.epam.pages.verifications;

import com.epam.pages.actions.HubspotLoginPagePassword;
import org.openqa.selenium.WebDriver;

public class HubspotLoginPagePasswordVerification extends HubspotLoginPagePassword {

    public HubspotLoginPagePasswordVerification (WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyPasswordFieldDisplay(String errMsg, boolean isDisplayed, String ... formatArgs) {
        verifyIsElementDisplayed(errMsg, getPasswordField(formatArgs), isDisplayed, "PasswordField" );
    }

    public void verifyPasswordFieldPresence(String errMsg, boolean isPresent, String ... formatArgs) {
        verifyIsElementPresent(errMsg, isPresent, getPasswordField(formatArgs), "PasswordField");
    }

    public void verifyLogInDisplay(String errMsg, boolean isDisplayed, String ... formatArgs) {
        verifyIsElementDisplayed(errMsg, getLogIn(formatArgs), isDisplayed, "LogIn" );
    }

    public void verifyLogInPresence(String errMsg, boolean isPresent, String ... formatArgs) {
        verifyIsElementPresent(errMsg, isPresent, getLogIn(formatArgs), "LogIn");
    }

}