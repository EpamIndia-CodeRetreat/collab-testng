package com.epam.stepdefinitions;

import com.epam.pages.actions.SwagLabsLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.epam.framework.ui.driver.DriverManager.getDriver;

public class swagLabsStepDef {

    private final SwagLabsLoginPage swagLabsLoginPage = new SwagLabsLoginPage(getDriver());

    public swagLabsStepDef() {
    }

    @Given("I am on the swag labs login page")
    public void openSwagLabsPage(){
        System.out.println("********* inside step def *****************");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        System.out.println("Testing opening of Swag Labs");
        getDriver().get("https://www.saucedemo.com/");
        getDriver().manage().window().maximize();
    }

    @When("I enter the {string} and {string} for logging in to the swag labs")
    public void iEnterTheAndForLoggingInToTheSwagLabs(String username, String password) {
        swagLabsLoginPage.clearuserNameTxt("User name text box emptied");
        swagLabsLoginPage.setuserNameTxt(username, "Entered the user name");
        swagLabsLoginPage.clearpasswordTxt("password text box emptied");
        swagLabsLoginPage.setpasswordTxt(password, "Entered password");
    }


    @When("I click on the swag labs login button")
    public void iClickOnTheSwagLabsLoginButton() {
        swagLabsLoginPage.clickloginButton("Login button clicked");
    }

    @When("the cost of the item is below {string} it is added to cart")
    public void theCostOfTheItemIsBelowItIsAddedToCart(String priceLimit) {
        
    }

    @Then("verify cart icon has notifications")
    public void verifyCartIconHasNotifications() {
    }
}
