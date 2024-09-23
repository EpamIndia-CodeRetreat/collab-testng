package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;

public class SwagLabsCheckoutFinishPage extends PageWrapper {

    private static final String totalItemPriceLabel = "xpath -> //div[@class='summary_subtotal_label']";
    private static final String totalTaxLabel = "xpath -> //div[@class='summary_tax_label']";
    private static final String totalBillLabel = "xpath -> //div[@class='summary_total_label']";
    private static final String finishButton = "xpath -> //button[@id='finish']";
    private static final String checkoutSuccess = "xpath -> //img[@alt='Pony Express']";

    public SwagLabsCheckoutFinishPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getTotalItemPriceLabel(String... formatArgs) {
        return getText(totalItemPriceLabel, "total item price is ", formatArgs);
    }

    public String getTotalTaxLabel(String... formatArgs) {
        return getText(totalTaxLabel, "total tax is ", formatArgs);
    }

    public String getTotalBillLabel(String... formatArgs) {
        return getText(totalBillLabel, "total bill is ", formatArgs);
    }

    public void clickFinishButton(String... formatArgs){
        click(finishButton,"Finish Button ", formatArgs);
    }

    public String getCheckoutSuccess(String... formatArgs) {
        return formatLocator(checkoutSuccess, formatArgs);
    }
    public void verifySuccessfulCheckout(String errMsg, boolean isDisplayed, String... formatArgs) {
        verifyIsElementDisplayed(errMsg, getCheckoutSuccess(), isDisplayed, "checkout success" );
    }
}
