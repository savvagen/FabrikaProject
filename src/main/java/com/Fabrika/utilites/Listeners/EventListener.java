package com.Fabrika.utilites.Listeners;



import com.Fabrika.UiObjects.Website;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

public class EventListener implements WebDriverEventListener {


    private Log logger = LogFactory.getLog(this.getClass());
    private By lastFindBy;
    private String originalValue;

    private static final Logger log = Logger.getLogger(EventListener.class);




    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        log.info("WebDriver navigating to:'" + url + "'");
    }

    @Override
    public void afterNavigateTo(String url, WebDriver webDriver) {
        log.info("WebDriver Navigated to'" + url + "'");
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {
        log.info("Navigated back to previous page");
    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {
        log.info("Navigated back to previous page");
    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {
        log.info("Navigating forward to next page");
    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {
        log.info("Navigated forward to page:'" + webDriver.getCurrentUrl() + "'");
    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        lastFindBy = by;
        log.info("Trying to find element: " + lastFindBy);
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        lastFindBy = by;
        log.info("Element : " + lastFindBy + " is found");
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        log.info("Trying to click on: " + webElement.toString() + " " + webElement.getText());
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        log.info("Clicked on: " + webElement.toString() + " " + webElement.getText());
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        originalValue = webElement.getAttribute("value");
        log.info("Trying to enter values to element: " + webElement.toString() + webElement.getText());
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        originalValue = webElement.getAttribute("value");
        log.info("Values are entered : " + "\"" + originalValue + "\"" + " to the element: " + webElement.toString());
        //log.debug("WebDriver changing value in element found "+lastFindBy+" from '"+originalValue+"' to '"+webElement.getAttribute("value")+"'");
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable error, WebDriver webDriver) {
        makeScreenshot(webDriver);
        log.error("Exception occured: " + error.getMessage());
        if (error.getClass().equals(NoSuchElementException.class)) {
            log.error("WebDriver error: Element not found " + lastFindBy);
        } else if (error.getClass().equals(AssertionError.class)) {
            log.error("WebDriver error: Assertion error found " + lastFindBy);
        } else if (error.getClass().equals(TimeoutException.class)) {
            log.error("WebDriver error: Exception fount waiting for element " + lastFindBy);
        } else if (error.getClass().equals(Exception.class)) {
            log.error("WebDriver error: Exception was found " + lastFindBy);
        } else {
            log.error("WebDriver error:", error);
        }
    }

    @Attachment
    public byte[] makeScreenshot(WebDriver webDriver) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }





}