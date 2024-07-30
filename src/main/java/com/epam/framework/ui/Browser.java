package com.epam.framework.ui;

import com.epam.framework.core.TestContext;
import com.epam.framework.core.constants.WaitConstants;
import com.epam.framework.core.logging.logger.LogLevel;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class Browser {

    protected WebDriver webDriver;
    protected FluentWait<WebDriver> fluentWait;
    public static TestContext testContext;
    protected JavaScriptExecutorUtils javascriptExecutor;
    protected WebDriverWait webDriverWait;
    private String defaultWindowHandle;

    private AlertUtils alert;
    private DragAndDrop dragAndDrop;

    public Browser(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        fluentWait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(WaitConstants.DEFAULT))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        javascriptExecutor = new JavaScriptExecutorUtils(webDriver, testContext);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(WaitConstants.DEFAULT));
        testContext = new TestContext();
        alert = new AlertUtils(webDriver, testContext);
        dragAndDrop= new DragAndDrop(webDriver, testContext);
    }

    public void get(String url) {
        webDriver.manage().window().maximize();
        webDriver.get(url);
        SyncUtils.waitUntilPageIsFullyLoaded(webDriver,WaitConstants.DEFAULT);
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public String getPageTitle(){
        return webDriver.getTitle();
    }

    public void refreshPage() {
        SyncUtils.waitUntilPageIsFullyLoaded(webDriver,WaitConstants.DEFAULT);
        webDriver.navigate().refresh();
        SyncUtils.waitUntilPageIsFullyLoaded(webDriver,WaitConstants.DEFAULT);
    }

    //wait on browser level
    public void implicitWait(long timeInMs) {
        webDriver.manage().timeouts().implicitlyWait(timeInMs, TimeUnit.MILLISECONDS);
    }

    public List<WebElement> fluentWait(By elementLocatedBy) {
        return fluentWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocatedBy));
    }


    //scroll functionalities
    public void scrollToTopOfPage(){
        SyncUtils.waitUntilPageIsFullyLoaded(webDriver,WaitConstants.DEFAULT);
        javascriptExecutor.executeScript("window.scrollTo(0,0)");
        testContext.getLogger().log(LogLevel.INFO, "The page is scrolled to the Top");
    }

    public void scrollToBottomOfPage(){
        SyncUtils.waitUntilPageIsFullyLoaded(webDriver,WaitConstants.DEFAULT);
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        testContext.getLogger().log(LogLevel.INFO, "The page is scrolled to Bottom");
    }

    //cookies
    public Set<Cookie> getBrowserCookies(){
        testContext.getLogger().log(LogLevel.INFO, "Retrieving Browser cookies");
        return webDriver.manage().getCookies();
    }

    //alert
    public AlertUtils alerts()
    {
        return this.alert;
    }

    public JavaScriptExecutorUtils getJavaScriptExecutor()
    {
        return this.javascriptExecutor;
    }

    public DragAndDrop getDragAndDrop()
    {
        return this.dragAndDrop;
    }

    //window

    public void switchToWindowWithIndex(String index){
        defaultWindowHandle = (defaultWindowHandle == null) ? webDriver.getWindowHandle() : defaultWindowHandle;
        testContext.getLogger().log(LogLevel.INFO, "Switching to window");
        webDriver.switchTo().window(index);
        SyncUtils.waitUntilPageIsFullyLoaded(webDriver,WaitConstants.DEFAULT);
    }

    public void switchToWindowWithTitle(String windowTitle){
        webDriver.getWindowHandles().stream().anyMatch(winHandle -> {
            switchToWindowWithIndex(winHandle);
            SyncUtils.waitUntilPageIsFullyLoaded(webDriver,WaitConstants.DEFAULT);
            return getPageTitle().equalsIgnoreCase(windowTitle);
        });
    }

    public void switchToDefaultWindow(){
        testContext.getLogger().log(LogLevel.INFO, "Switching to default window");
        webDriver.switchTo().window(defaultWindowHandle);
        SyncUtils.waitUntilPageIsFullyLoaded(webDriver,WaitConstants.DEFAULT);
    }
}
