package com.epam.framework.ui;

import com.epam.framework.core.TestContext;
import com.epam.framework.core.logging.logger.LogLevel;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertUtils {
    private WebDriver webDriver;
    private TestContext testContext;

    public AlertUtils(WebDriver webDriver, TestContext testContext) {
        this.webDriver = webDriver;
        this.testContext=testContext;
    }

    private Alert switchToAlert(){
        testContext.getLogger().log(LogLevel.INFO, "Switched to Alert pop-up");
        return webDriver.switchTo().alert();
    }

    public void cancelAlert(){
        switchToAlert().dismiss();
        testContext.getLogger().log(LogLevel.INFO, "The Alert is Cancelled");
    }

    public void acceptAlert(){
        switchToAlert().accept();
        testContext.getLogger().log(LogLevel.INFO, "The Alert is Accepted");
    }

    public String getTextFromAlert(){
        testContext.getLogger().log(LogLevel.INFO, "Retrieving text from Alert");
        return switchToAlert().getText();
    }

    public void sendTextToAlert(String input){
        switchToAlert().sendKeys(input);
        testContext.getLogger().log(LogLevel.INFO, "Text is inputted to the Alert");
    }
}
