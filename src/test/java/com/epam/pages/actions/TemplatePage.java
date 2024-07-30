package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class TemplatePage extends PageWrapper {

    private static final String variables = "xpath -> //img[normalize-space(@src)='./../../../assets/icons/variables.svg']";
    private static final String selectOne = "xpath -> //span[normalize-space(text())='Select one']";
    private static final String deal = "xpath -> //span[normalize-space(text())='Deal']";
    private static final String addDataSource = "xpath -> //button[normalize-space(@class)='btn btn-primary mb-3']";
    private static final String publishTemplate = "xpath -> //button[normalize-space(@class)='btn btn-primary publish-btn']";
    private static final String untitledDocument = "xpath -> //div[normalize-space(@title)='Untitled Document']";
    private static final String updateTemplate = "xpath -> //button[normalize-space(@class)='btn btn-primary publish-btn']";

    public TemplatePage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getVariables(String ... formatArgs) {
        return formatLocator(variables, formatArgs);
    }

    public String getSelectOne(String ... formatArgs) {
        return formatLocator(selectOne, formatArgs);
    }

    public String getDeal(String ... formatArgs) {
        return formatLocator(deal, formatArgs);
    }

    public String getAddDataSource(String ... formatArgs) {
        return formatLocator(addDataSource, formatArgs);
    }

    public String getPublishTemplate(String ... formatArgs) {
        return formatLocator(publishTemplate, formatArgs);
    }

    public String getUntitledDocument(String ... formatArgs) {
        return formatLocator(untitledDocument, formatArgs);
    }

    public String getUpdateTemplate(String ... formatArgs) {
        return formatLocator(updateTemplate, formatArgs);
    }

    public void clickVariables(String... formatArgs){
        click(variables, "variables",formatArgs);
    }

    public void selectSelectOneByVisibleText(String optionText, String... formatArgs){
        selectItemFromDropdownByVisibleText(optionText, selectOne,"selectOne", formatArgs);
    }

    public void clickDeal(String... formatArgs){
        click(deal, "deal",formatArgs);
    }

    public void clickAddDataSource(String... formatArgs){
        click(addDataSource, "addDataSource",formatArgs);
    }

    public void clickPublishTemplate(String... formatArgs){
        click(publishTemplate, "publishTemplate",formatArgs);
    }

    public void setUntitledDocument(String value, String ... formatArgs) {
        setText(value, untitledDocument,"UntitledDocument", formatArgs);
    }

    public void clickUpdateTemplate(String... formatArgs){
        click(updateTemplate, "updateTemplate",formatArgs);
    }

}
