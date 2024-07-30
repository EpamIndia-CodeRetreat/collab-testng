package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;

public class GmailLoginPage extends PageWrapper {
    private static final String username= "xpath -> //input[@type='email']";
    private static final String nextForUsername= "xpath -> //*[@id='identifierNext']//span[contains(text(),'Next')]";
    private static final String password= "xpath -> //input[@type='password']";
    private static final String nextForPassword= "xpath -> //*[@id='passwordNext']//span[contains(text(),'Next')]";


    public GmailLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void setUsername(String value, String ... formatArgs) {
        setText(value, username,"Username", formatArgs);
    }
    public void setPassword(String value, String ... formatArgs) {
        setText(value, password,"Password", formatArgs);
    }

    public void clickNextButtonForUsername(String... formatArgs){
        click(nextForUsername, "nextForUsername",formatArgs);
    }

    public void clickNextButtonForPassword(String... formatArgs){
        click(nextForPassword, "nextForPassword",formatArgs);
    }
}
