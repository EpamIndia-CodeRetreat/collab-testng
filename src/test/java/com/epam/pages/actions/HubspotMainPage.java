package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class HubspotMainPage extends PageWrapper {

    private static final String crm = "xpath -> //span[normalize-space(text())='CRM']";
    private static final String deals = "xpath -> //span[normalize-space(text())='Deals']";

    public HubspotMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getCrm(String ... formatArgs) {
        return formatLocator(crm, formatArgs);
    }

    public String getDeals(String ... formatArgs) {
        return formatLocator(deals, formatArgs);
    }

    public void clickCrm(String... formatArgs){
        click(crm, "crm",formatArgs);
    }

    public void clickDeals(String... formatArgs){
        click(deals, "deals",formatArgs);
    }

}
