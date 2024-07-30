package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class DealsInHubspot extends PageWrapper {

    private static final String createDeal = "xpath -> //span[normalize-space(text())='Create deal']";
    private static final String dealNameTextarea = "xpath -> //textarea[@id='UIFormControl-1']";
    private static final String createDealButton = "xpath -> //button[@data-test-id='create-button']//span//i18n-string";

    public DealsInHubspot(WebDriver webDriver) {
        super(webDriver);
    }

    public String getCreateDeal(String ... formatArgs) {
        return formatLocator(createDeal, formatArgs);
    }

    public String getdealNameTextarea(String ... formatArgs) {
        return formatLocator(dealNameTextarea, formatArgs);
    }

    public String getcreateDealButton(String ... formatArgs) {
        return formatLocator(createDealButton, formatArgs);
    }

    public void clickCreateDeal(String... formatArgs){
        click(createDeal, "createDeal",formatArgs);
    }

    public String getCreateDealText(String... formatArgs){
        return getText(createDeal,"createDeal", formatArgs);
    }

    public void setdealNameTextarea(String value, String ... formatArgs) {
        setText(value, dealNameTextarea,"dealNameTextarea", formatArgs);
    }

    public void clickcreateDealButton(String... formatArgs){
        click(createDealButton, "createDealButton",formatArgs);
    }

}
