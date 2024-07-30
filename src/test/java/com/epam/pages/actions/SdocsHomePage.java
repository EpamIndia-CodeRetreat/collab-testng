package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class SdocsHomePage extends PageWrapper {

    private static final String welcomeToSDocs = "xpath -> //h4[contains(normalize-space(.),'Welcome to S-Docs!')]";
    private static final String startATemplate = "xpath -> //button[normalize-space(@class)='btn btn-primary float-end']";
    private static final String createNewStartANewBlankTemplate = "xpath -> //div[contains(normalize-space(.),'Create new Start a new blank template')][normalize-space(@class)='col-8 ps-3']";

    public SdocsHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getWelcomeToSDocs(String ... formatArgs) {
        return formatLocator(welcomeToSDocs, formatArgs);
    }

    public String getStartATemplate(String ... formatArgs) {
        return formatLocator(startATemplate, formatArgs);
    }

    public String getCreateNewStartANewBlankTemplate(String ... formatArgs) {
        return formatLocator(createNewStartANewBlankTemplate, formatArgs);
    }

    public String getWelcomeToSDocsAttribute(String attributeName, String... formatArgs){
        return getAttribute(attributeName, welcomeToSDocs,"welcomeToSDocs", formatArgs);
    }

    public void clickStartATemplate(String... formatArgs){
        click(startATemplate, "startATemplate",formatArgs);
    }

    public void clickCreateNewStartANewBlankTemplate(String... formatArgs){
        click(createNewStartANewBlankTemplate, "createNewStartANewBlankTemplate",formatArgs);
    }

}
