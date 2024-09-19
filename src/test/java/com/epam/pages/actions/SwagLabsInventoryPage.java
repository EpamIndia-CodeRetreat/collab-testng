package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class SwagLabsInventoryPage extends PageWrapper {

    public SwagLabsInventoryPage(WebDriver webDriver) {
        super(webDriver);
    }

    private static final String inventoryParent = "xpath -> //div[@class='inventory_list']";
    private static final String inventoryChildItems = "xpath -> //div[@class='inventory_item']";

    //Added incomplete bracket to the xpath this will be completed when we get the desired label
    private static final String inventoryLabels = "xpath -> (//div[@class='inventory_item_price']";

    private static final String shoppingCartIcon = "xpath -> //a[@class='shopping_cart_link']/span";

    public int getNoOfInventoryItems(){
        return getElements(inventoryChildItems).size();
    }
    public String getInventoryParent(String ... formatArgs) {
        return formatLocator(inventoryParent, formatArgs);
    }

    public String getInventoryParentLabel(String ... formatArgs) {
        return formatLocator(inventoryParent, formatArgs);
    }

    public String getDesiredLabelText(int labelIndex, String... formatArgs){
        String inventoryLabelsFinalXpath = "";
        inventoryLabelsFinalXpath =  inventoryLabels + ")[" + String.valueOf(labelIndex) + "]";
        return getText(inventoryLabelsFinalXpath, "Price Label", formatArgs);
    }

    public void clickDesiredButton(int labelIndex, String... formatArgs){
        String addToCartButtonFinalXpath = "";
        addToCartButtonFinalXpath =  inventoryLabels + ")[" + String.valueOf(labelIndex) + "]" + "/following-sibling::button[@class='btn btn_primary btn_small btn_inventory ']";
        click(addToCartButtonFinalXpath , "Add respective item to cart", formatArgs);
    }

    public String getNotificationIconText(String... formatArgs) {
        return getText(shoppingCartIcon, "shopping cart icon display", formatArgs);
    }

    public void clickShoppingCartIcon(String... formatArgs){
        click(shoppingCartIcon, "shopping cart icon", formatArgs);
    }
}
