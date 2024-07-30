package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;

public class GmailFinal extends PageWrapper {
    private static final String securityPopup = "xpath -> //button[@class='xFITmb']";
    private static final String otpField = "xpath -> //input[@id='code']";
    //private static final String frameToSwitch= "xpath -> (//iframe[@tabindex='-1'])[1]";
    private static final String alert = "xpath -> (//iframe[@role='presentation'])[3]";

    public GmailFinal(WebDriver webDriver) {
        super(webDriver);
    }

    public String getLatestEmailText(String... formatArgs) {
        // return getText(latestEmail,"latestEmail", formatArgs);
        return "";
    }

    public void clickLatestEmail(String... formatArgs) {
        // click(latestEmail, "latestEmail",formatArgs);
    }

    public void clickSecurityPopup(String... formatArgs) {
        click(securityPopup, "securityPopup", formatArgs);
    }

    public void setOtpField(String value, String... formatArgs) {
        setText(value, otpField, "otpField", formatArgs);
    }

    public void switchingToAlert() {
        switchToIframeByElement(alert);
    }
//    public void switchingToFrame(){
//        switchToIframeByElement(frameToSwitch);
//    }

}
