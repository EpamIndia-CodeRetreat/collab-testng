package com.epam.pages.verifications;

import com.epam.pages.actions.SdocsHomePage;
import org.openqa.selenium.WebDriver;

public class SdocsHomePageVerification extends SdocsHomePage {

    public SdocsHomePageVerification (WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyWelcomeToSDocsDisplay(String errMsg, boolean isDisplayed, String ... formatArgs) {
        verifyIsElementDisplayed(errMsg, getWelcomeToSDocs(formatArgs), isDisplayed, "WelcomeToSDocs" );
    }

    public void verifyStartATemplateText(String errMsg, String expectedValue, String... formatArgs) {
        verifyElementText(errMsg, getStartATemplate(formatArgs), expectedValue);
    }

    public void verifyStartATemplateDisplay(String errMsg, boolean isDisplayed, String ... formatArgs) {
        verifyIsElementDisplayed(errMsg, getStartATemplate(formatArgs), isDisplayed, "StartATemplate" );
    }

}