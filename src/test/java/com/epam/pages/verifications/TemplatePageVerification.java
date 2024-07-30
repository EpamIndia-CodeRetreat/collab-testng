package com.epam.pages.verifications;

import com.epam.pages.actions.TemplatePage;
import org.openqa.selenium.WebDriver;

public class TemplatePageVerification extends TemplatePage {

    public TemplatePageVerification (WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyAddDataSourceDisplay(String errMsg, boolean isDisplayed, String ... formatArgs) {
        verifyIsElementDisplayed(errMsg, getAddDataSource(formatArgs), isDisplayed, "AddDataSource" );
    }

    public void verifyPublishTemplateDisplay(String errMsg, boolean isDisplayed, String ... formatArgs) {
        verifyIsElementDisplayed(errMsg, getPublishTemplate(formatArgs), isDisplayed, "PublishTemplate" );
    }

    public void verifyPublishTemplatePresence(String errMsg, boolean isPresent, String ... formatArgs) {
        verifyIsElementPresent(errMsg, isPresent, getPublishTemplate(formatArgs), "PublishTemplate");
    }

}