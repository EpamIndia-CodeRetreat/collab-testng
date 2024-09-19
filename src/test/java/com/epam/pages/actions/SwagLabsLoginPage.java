package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class SwagLabsLoginPage extends PageWrapper {

    private static final String userNameTxt = "id -> user-name";
    private static final String passwordTxt = "id -> password";
    private static final String loginButton = "id -> login-button";

    public SwagLabsLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getuserNameTxt(String ... formatArgs) {
        return formatLocator(userNameTxt, formatArgs);
    }

    public String getpasswordTxt(String ... formatArgs) {
        return formatLocator(passwordTxt, formatArgs);
    }

    public String getloginButton(String ... formatArgs) {
        return formatLocator(loginButton, formatArgs);
    }

    public String getuserNameTxtText(String ... formatArgs){
        return getText(userNameTxt,"userNameTxt", formatArgs);
    }

    public void clearuserNameTxt(String... formatArgs) {
        clearField(userNameTxt, "userNameTxt");
    }

    public void setuserNameTxt(String value, String ... formatArgs) {
        setText(value, userNameTxt,"userNameTxt",formatArgs);
    }

    public String getpasswordTxtText(String ... formatArgs){
        return getText(passwordTxt,"passwordTxt", formatArgs);
    }

    public void clearpasswordTxt(String... formatArgs) {
        clearField(passwordTxt, "passwordTxt");
    }
    public void setpasswordTxt(String value, String ... formatArgs) {
        setText(value, passwordTxt,"passwordTxt", formatArgs);
    }

    public void clickloginButton(String... formatArgs){
        click(loginButton, "loginButton");
    }

    public String getloginButtonText(String... formatArgs){
        return getText(loginButton,"loginButton", formatArgs);
    }

}
