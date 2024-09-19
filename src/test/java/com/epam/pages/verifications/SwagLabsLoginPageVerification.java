package com.epam.pages.verifications;

import com.epam.pages.actions.SwagLabsLoginPage;
import org.openqa.selenium.WebDriver;

public class SwagLabsLoginPageVerification extends SwagLabsLoginPage {

    public SwagLabsLoginPageVerification (WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyuserNameTxtPresence(String errMsg, boolean isPresent, String ... formatArgs) {
        verifyIsElementPresent(errMsg, isPresent, getuserNameTxt(formatArgs), "userNameTxt");
    }

    public void verifypasswordTxtText(String errMsg, String expectedValue, String... formatArgs) {
        verifyElementText(errMsg, getpasswordTxt(formatArgs), expectedValue);
    }

    public void verifypasswordTxtDisplay(String errMsg, boolean isDisplayed, String ... formatArgs) {
        verifyIsElementDisplayed(errMsg, getpasswordTxt(formatArgs), isDisplayed, "passwordTxt" );
    }

    public void verifypasswordTxtPresence(String errMsg, boolean isPresent, String ... formatArgs) {
        verifyIsElementPresent(errMsg, isPresent, getpasswordTxt(formatArgs), "passwordTxt");
    }

    public void verifypasswordTxtValue(String errMsg, String expValue, String ... formatArgs) {
        verifyElementValue(errMsg, getpasswordTxt(formatArgs),expValue);
    }

    public void verifyloginButtonText(String errMsg, String expectedValue, String... formatArgs) {
        verifyElementText(errMsg, getloginButton(formatArgs), expectedValue);
    }

    public void verifyloginButtonValue(String errMsg, String expValue, String ... formatArgs) {
        verifyElementValue(errMsg, getloginButton(formatArgs),expValue);
    }

    public void verifyloginButtonDisplay(String errMsg, boolean isDisplayed, String ... formatArgs) {
        verifyIsElementDisplayed(errMsg, getloginButton(formatArgs), isDisplayed, "loginButton" );
    }

    public void verifyloginButtonPresence(String errMsg, boolean isPresent, String ... formatArgs) {
        verifyIsElementPresent(errMsg, isPresent, getloginButton(formatArgs), "loginButton");
    }

}