package com.epam.framework.ui;

import com.epam.framework.core.TestContext;
import com.epam.framework.core.logging.logger.LogLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDrop {

    private WebDriver webDriver;
    private TestContext testContext;

    public DragAndDrop(WebDriver webDriver, TestContext testContext) {
        this.webDriver = webDriver;
        this.testContext=testContext;
    }

    private Actions getActions(){
        return new Actions(webDriver);
    }

    public void dragAndDropBy(WebElement from, WebElement to)
    {
        getActions().dragAndDrop(from, to).build().perform();
        testContext.getLogger().log(LogLevel.INFO, "Completed Drag and Drop Operation");
    }

    public void dragAndDropBy(WebElement from, int xOffset, int yOffset )
    {
        getActions().dragAndDropBy(from,xOffset,yOffset).build().perform();
        testContext.getLogger().log(LogLevel.INFO,
                String.format("Completed Drag and Drop Operation to offset (%s, %s)", xOffset, yOffset));
    }

    public void clickAndHoldElement(WebElement element)
    {
        getActions().moveToElement(element).clickAndHold(element).build().perform();
        testContext.getLogger().log(LogLevel.INFO, "Completed Click and Hold Operation");
    }

    public void moveByOffset(int xOffset, int yOffset )
    {
        getActions().moveByOffset(xOffset, yOffset).build().perform();
        testContext.getLogger().log(LogLevel.INFO,
                String.format("Completed moving to Offset (%s, %s)", xOffset, yOffset));
    }

    public void release()
    {
        getActions().release().build().perform();
        testContext.getLogger().log(LogLevel.INFO,"Completed release operation");
    }

    public void moveToElement(WebElement element)
    {
        getActions().moveToElement(element).build().perform();
        testContext.getLogger().log(LogLevel.INFO,"Completed moved to element operation");
    }
}
