package com.epam.stepdefinitions;

import com.epam.framework.ui.PageWrapper;
import com.epam.pages.actions.*;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.framework.ui.driver.DriverManager.getDriver;
import static junit.framework.TestCase.assertEquals;

public class hubspotLandingStepDef extends BaseStepDef {

    public WebDriver webDriver;
    private final HubspotLandingPage hubspotLandingPage = new HubspotLandingPage(getDriver());
    private final HubspotLoginPageEmail hubspotLoginPageEmail = new HubspotLoginPageEmail(getDriver());
    private final HubspotLoginPagePassword hubspotLoginPagePassword=new HubspotLoginPagePassword(getDriver());
    private final GmailFinal gmailFinal=new GmailFinal(getDriver());
    private final GmailLoginPage gmailLoginPage = new GmailLoginPage(getDriver());

    public hubspotLandingStepDef() {
    }
//
//        @Override
//        public void switchToIframeByElement(String locator, String... formatters) {
//            super.switchToIframeByElement(locator, formatters);
//        }



    @Given("I launch the url for hubspot")
    public void iLaunchTheUrlForHubspot() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        System.out.println("Testing opening of Hubspot");
        getDriver().get("https://www.hubspot.com/");
        //getDriver().get("https://app.hubspot.com/contacts/46451474/record/0-3/20526524851");
        //getDriver().get("https://app.hubspot.com/discover/46451474/library/dashboards");
        getDriver().manage().window().maximize();
    }

    @When("I click on the login link")
    public void iClickOnTheLoginLink() throws InterruptedException {
        Thread.sleep(5000);
        hubspotLandingPage.clickAcceptCookies();
        for (int i = 0; i < 5; i++) {
            try {
                hubspotLandingPage.clickLogin();
                break;
            } catch (Exception e) {
                Thread.sleep(5000);
                Thread.currentThread().interrupt();
            }
        }
        hubspotLandingPage.clickLogin();
        System.out.println("clicked on login");
    }

    @Then("I am taken to the sign in page {string}")
    public void iAmTakenToTheSignInPage(String message) {
    }

    @And("The sign in page is asserted")
    public void theSignInPageIsAsserted() {

    }

    @Then("I enter the username and password")
    public void iEnterTheUsername() {
        hubspotLoginPageEmail.setEmailField("testing.sdocs@gmail.com");
        //hubspotLoginPageEmail.setPasswordField("Srishti@testingsdocs");
        hubspotLoginPageEmail.setPasswordField("Srishti@testingsdocs");
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        //hubspotLoginPageEmail.clickLogin();
        hubspotLoginPageEmail.clickLogIn();
    }

    @Then("I should be asked OTP for login")
    public void iShouldBeNavigatedToTheHubspotHomepage() {
        System.out.println("Opening gmail in a new tab to fetch otp");
        getDriver().switchTo().newWindow(WindowType.TAB);
        getDriver().get("https://www.gmail.com");
        gmailLoginPage.setUsername("testing.sdocs@gmail.com");
        gmailLoginPage.clickNextButtonForUsername();
        gmailLoginPage.setPassword("Test@Sdocs");
        gmailLoginPage.clickNextButtonForPassword();
        System.out.println("Opened gmail");
        System.out.println("clicking on the security check in popup to dismiss");
        gmailFinal.switchingToAlert();
        gmailFinal.clickSecurityPopup();
        getDriver().switchTo().parentFrame();
        //switchToIframeByElement("(//iframe[@tabindex='-1'])[1]");
        System.out.println("Clicking on the latest email for otp");
        //gmailFinal.clickLatestEmail();
    }

    @Given("I am on gmail")
    public String iAmOnGmail() {
       // gmailFinal.getLatestEmailText();
       // System.out.println(gmailFinal.getLatestEmailText());
     //   String entireOtp=gmailFinal.getLatestEmailText();
//        Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
//        Matcher matcher = pattern.matcher(gmailFinal.getLatestEmailText());
//        if (matcher.find()) {
//            return matcher.group();
//        }
//        return null;
        return "";
    }

    @When("I fetch otp and enter in hubspot")
    public void iFetchOtpAndEnterInHubspot() {
        System.out.println("Closing gmail and returning to hubspot");
        getDriver().switchTo().window(getDriver().getWindowHandle());
        gmailFinal.setOtpField(iAmOnGmail());
        //gmailPage.switchBackToOtpPage(getDriver().getWindowHandle());
    }

    @Then("I am back on hubspot homepage")
    public void iAmBackOnHubspotHomepage() {

    }
}
