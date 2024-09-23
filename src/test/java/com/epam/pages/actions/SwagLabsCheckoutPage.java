package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;

public class SwagLabsCheckoutPage extends PageWrapper {

    public static  final String checkoutButton = "id -> checkout";

    public SwagLabsCheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnCheckoutButton(String... formatArgs){
        click(checkoutButton , "checkout button",formatArgs);
    }

}
