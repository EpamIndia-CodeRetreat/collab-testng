package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class currentDealPage extends PageWrapper {

    private String sdocsApplicationLink = "xpath -> //a[normalize-space(@href)='https://app.hubspot.test.sdocs.com/templates?token=Z%2F91iyj6H6xn%2BX1hc6Mkvp02HmYtIaFyvlEkxsWKNbEKmSiXfODLz%2BZs%2Be9PMI6hHBwQQsc1eM8vskan0IchoarKs5yulz1MzV3lgPt0%2FtV6D%2FBzgtnWDnkYn2OQKXqHKjRDs%2FnfZTeY%2BxWuvYOswM5o4rTCyOX1NdOZwH6Ukpe32xzXW1VLVcfCv%2FVOyRADb1NKvtseb%2FUmzwss8CLK9MWu%2BUHSoSlEBqqB0bfP39NeapgK9Yh13g%2BYdHpuHfvFe1iEUAUv7evuPfIprdgzm%2FP1QRlcGWsyXIRR15mNb1gAuYZSs1ywwy6Udd7XLjMgjTSLFvQNyTEgL7zDPlzsx6aw7gLU8H9cPav85QgpAVV5hyPoiyeyxPqw1TFp6Q3TFAZINbrwLmgGS3S%2BrFiArliT3BM8xnfw%2FvSf1pFxLyA%3D']";
    private static final String activities = "xpath -> //div[normalize-space(@class)='UITabs__StyledList-sc-1xiqy2a-0 iNrXKH private-tabs__list']/a[normalize-space(@class)='private-link uiLinkWithoutUnderline UITab__StyledLink-sc-15za6r0-2 jeLTxY private-tab private-tab--active private-link--unstyled']";

    public currentDealPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getsdocsApplicationLink(String ... formatArgs) {
        return formatLocator(sdocsApplicationLink, formatArgs);
    }

    public String getActivities(String ... formatArgs) {
        return formatLocator(activities, formatArgs);
    }

    public void clicksdocsApplicationLink(String... formatArgs){
        click(sdocsApplicationLink, "sdocsApplicationLink",formatArgs);
    }

    public String getActivitiesText(String... formatArgs){
        return getText(activities,"activities", formatArgs);
    }

}
