package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class HubspotLoginPageEmail extends PageWrapper {

    private static final String emailField = "xpath -> //input[@id='username']";
    private static final String nextButton = "xpath -> //i18n-string[normalize-space(text())='Next']";
    private static final String passwordField = "xpath -> //input[@id='password']";
    private static final String logIn = "xpath -> //i18n-string[normalize-space(text())='Log in']";

    public HubspotLoginPageEmail(WebDriver webDriver) {
        super(webDriver);
    }

    public String getEmailField(String ... formatArgs) {
        return formatLocator(emailField, formatArgs);
    }

    public String getNextButton(String ... formatArgs) {
        return formatLocator(nextButton, formatArgs);
    }

    public void setEmailField(String value, String ... formatArgs) {
        setText(value, emailField,"EmailField", formatArgs);
    }
    public void setPasswordField(String value, String ... formatArgs) {
        setText(value, passwordField,"PasswordField", formatArgs);
    }

    public void clickLogIn(String... formatArgs){
        click(logIn, "logIn",formatArgs);
    }

    public void clickNextButton(String... formatArgs){
        click(nextButton, "nextButton",formatArgs);
    }

}
