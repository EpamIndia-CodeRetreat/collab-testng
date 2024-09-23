package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;

public class SwagLabsEnterCheckoutInfo extends PageWrapper {

    private static final String firstName = "id -> first-name";
    private static final String lastName = "id -> last-name";
    private static final String postalCode = "id -> postal-code";
    private static final String continueButton = "xpath -> //input[@type='submit']";

    public SwagLabsEnterCheckoutInfo(WebDriver webDriver) {
        super(webDriver);
    }

    public void setFirstName(String value, String ... formatArgs) {
        setText(value, firstName,"first name ",formatArgs);
    }

    public void setLastName(String value, String ... formatArgs) {
        setText(value, lastName,"last name ",formatArgs);
    }

    public void setPostalCode(String value, String ... formatArgs) {
        setText(value, postalCode,"postal code ",formatArgs);
    }

    public void clickContinueBtn(String... formatArgs){
        click(continueButton, "Continue Button ", formatArgs);
    }

}
