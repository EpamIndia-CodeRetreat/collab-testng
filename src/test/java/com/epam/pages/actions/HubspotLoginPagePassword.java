package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class HubspotLoginPagePassword extends PageWrapper {

    private static final String passwordField = "xpath -> //input[@id='password']";
    private static final String logIn = "xpath -> //i18n-string[normalize-space(text())='Log in']";

    public HubspotLoginPagePassword(WebDriver webDriver) {
        super(webDriver);
    }

    public String getPasswordField(String ... formatArgs) {
        return formatLocator(passwordField, formatArgs);
    }

    public String getLogIn(String ... formatArgs) {
        return formatLocator(logIn, formatArgs);
    }

    public void clearPasswordField(String... formatArgs) {
        clearField(passwordField,"PasswordField", formatArgs);
    }

    public void setPasswordField(String value, String ... formatArgs) {
        setText(value, passwordField,"PasswordField", formatArgs);
    }

    public void clickLogIn(String... formatArgs){
        click(logIn, "logIn",formatArgs);
    }

}
