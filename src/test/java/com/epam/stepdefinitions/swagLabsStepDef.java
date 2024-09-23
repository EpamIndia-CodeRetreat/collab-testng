package com.epam.stepdefinitions;

import com.epam.framework.core.logging.logger.LogLevel;
import com.epam.framework.core.reporting.Reporter;
import com.epam.pages.actions.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import static com.epam.framework.ui.driver.DriverManager.getDriver;

public class swagLabsStepDef {

    private final SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver());
    private final SwagLabsInventoryPage swagLabsInventoryPage = new SwagLabsInventoryPage(getDriver());
    private final SwagLabsCheckoutPage swagLabsCheckoutPage = new SwagLabsCheckoutPage(getDriver());
    private final SwagLabsEnterCheckoutInfo swagLabsEnterCheckoutInfoPage = new SwagLabsEnterCheckoutInfo(getDriver());
    private final SwagLabsCheckoutFinishPage swagLabsCheckoutFinishPage = new SwagLabsCheckoutFinishPage(getDriver());


    private int noOfItemsClicked = 0;
    private float totalItemPrice = 0.0F;

    public swagLabsStepDef() {
    }

    @Given("I am on the swag labs login page")
    public void openSwagLabsPage(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        System.out.println("Testing opening of Swag Labs");
        getDriver().get("https://www.saucedemo.com/");
        getDriver().manage().window().maximize();
    }

    @When("I enter the {string} and {string} for logging in to the swag labs")
    public void iEnterTheAndForLoggingInToTheSwagLabs(String username, String password) {
        swagLabsLoginPage.setuserNameTxt(username);
        swagLabsLoginPage.setpasswordTxt(password);
    }


    @When("I click on the swag labs login button")
    public void iClickOnTheSwagLabsLoginButton() {
        swagLabsLoginPage.clickloginButton();
    }

    @When("the cost of the item is below {string} it is added to cart")
    public void theCostOfTheItemIsBelowItIsAddedToCart(String priceLimit) {
        float priceCap = Float.parseFloat(priceLimit.trim());

        for(int itemCnt=0; itemCnt < swagLabsInventoryPage.getNoOfInventoryItems(); itemCnt++){
            String itemPriceText = "";
            itemPriceText = swagLabsInventoryPage.getDesiredLabelText(itemCnt +1).replace("$","");
            float itemPrice = Float.parseFloat(itemPriceText);
            if(itemPrice < priceCap) {
                swagLabsInventoryPage.clickDesiredButton(itemCnt+1);
                noOfItemsClicked++;
                totalItemPrice += itemPrice;
            }
        }
    }

    @Then("verify cart icon has notifications")
    public void verifyCartIconHasNotifications() {
        String notificationIconText = swagLabsInventoryPage.getNotificationIconText();

        if(Integer.parseInt(notificationIconText) == noOfItemsClicked) {
            Reporter.log(LogLevel.INFO, "Notification icon is displaying the no of items in cart correctly");
            Reporter.pass("Items added to shopping cart correctly");
        }
        swagLabsInventoryPage.clickShoppingCartIcon();
    }

    @Then("click on checkout button")
    public void clickOnCheckoutButton() {
        swagLabsCheckoutPage.clickOnCheckoutButton();
    }

    @Then("enter checkout information {string} {string} and {string}")
    public void enterCheckoutInformationAnd(String firstName, String lastName , String postalCode) {
        swagLabsEnterCheckoutInfoPage.setFirstName(firstName);
        swagLabsEnterCheckoutInfoPage.setLastName(lastName);
        swagLabsEnterCheckoutInfoPage.setPostalCode(postalCode);
        swagLabsEnterCheckoutInfoPage.clickContinueBtn();
    }

    @Then("verify the total bill")
    public void verifyTheTotalBill() {
        String totalItemPriceStr = swagLabsCheckoutFinishPage.getTotalItemPriceLabel();
        String totalTaxStr = swagLabsCheckoutFinishPage.getTotalTaxLabel();
        String totalBillStr = swagLabsCheckoutFinishPage.getTotalBillLabel();

        float totalPriceOfItems = Float.parseFloat(totalItemPriceStr.split("\\$")[1]);
        Assert.assertEquals(totalPriceOfItems , totalItemPrice);
        Reporter.pass("Item price total is calculated correctly");

        float totalTaxOnItems = Float.parseFloat(totalTaxStr.split("\\$")[1]);
        float totalBillOfItems  = totalPriceOfItems + totalTaxOnItems;

        Assert.assertEquals(Float.parseFloat(totalBillStr.split("\\$")[1]), totalBillOfItems);
        Reporter.pass("Total Bill is calculated correctly");

        swagLabsCheckoutFinishPage.clickFinishButton();

        swagLabsCheckoutFinishPage.verifySuccessfulCheckout("Checkout image is not displayed", true);
    }
}
