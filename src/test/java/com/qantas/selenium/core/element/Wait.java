package com.qantas.selenium.core.element;


import com.qantas.selenium.core.CommonExpectedConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Wait {

    /**
     * Checks if the element is present in browser DOM
     */

    private static final int DEFAULT_TIMEOUT = 15;
    private static final int DEFAULT_SLEEP = 5000;
    private static final String INIT_MESSAGE = "INIT ELEMENT";
    private static final String INIT_ERROR_MESSAGE = "PROBLEM WITH ELEMENT INIT";
    private static final String ELEMENT_PRESENT_MESSAGE = "ELEMENT PRESENT";
    private static final String ELEMENT_PRESENT_ERROR_FORMAT = "PROBLEM WITH FINDING ELEMENT %s";

    private WebDriverWait wait;
    private WebDriverWait sleepingWait;
    private WebDriver driver;

    public Wait(WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT);
        this.sleepingWait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT, DEFAULT_SLEEP);
    }

    public void wait(int timeoutsec) {
        new WebDriverWait(driver, timeoutsec);
    }

    /**
     * Checks if the element is present in browser DOM
     */
    public WebElement forElementPresent(By by) {
        return forElementPresent(by, true);
    }

    /**
     * Checks if the element is present in browser DOM
     */
    public WebElement forElementPresent(By by, boolean failOnTimeout) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            if (failOnTimeout) {

            }

            throw e;
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Checks if the element is present in browser DOM
     */
    public WebElement forElementPresent(By by, int timeout) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeout).until(ExpectedConditions
                    .presenceOfElementLocated(by));
        } catch (TimeoutException e) {

            throw e;
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Checks if the element is clickable in browser
     *
     * @param element The element to be checked
     */
    public WebElement forElementClickable(WebElement element) {
        changeImplicitWait(0, TimeUnit.MILLISECONDS);

        try {
                return wait.until(ExpectedConditions.elementToBeClickable(element));
            }
         finally {
            restoreDeaultImplicitWait();
        }
    }

    public WebElement forElementClickable(WebElement element, int timeout) {
        changeImplicitWait(0, TimeUnit.MILLISECONDS);
        try {
            element.getTagName();
        } catch (WebDriverException e) {

        }
        try {

            return new WebDriverWait(driver, timeout).until(ExpectedConditions
                    .elementToBeClickable(element));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public WebElement forElementClickable(List<WebElement> elements, int index, int timeout) {
        changeImplicitWait(0, TimeUnit.MILLISECONDS);
        try {
            elements.get(index).getTagName();
        } catch (WebDriverException e) {

        }
        try {

            return new WebDriverWait(driver, timeout).until(
                    ExpectedConditions.elementToBeClickable(elements.get(index)));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Checks if the element is clickable on the browser
     */
    public WebElement forElementClickable(By by) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Checks if the element is clickable on the browser
     */
    public WebElement forElementClickable(By by, int timeout) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeout).until(ExpectedConditions
                    .elementToBeClickable(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public WebElement forElementVisible(WebElement element, int timeoutSec, int polling) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeoutSec, polling).until(ExpectedConditions
                    .visibilityOf(element));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public WebElement forElementVisible(WebElement element, int timeoutSec) {
        return forElementVisible(element, timeoutSec, 500);
    }

    public WebElement forElementVisible(By selector, int timeoutSec) {
        return forElementVisible(selector, timeoutSec, 500);
    }

    public WebElement forElementVisible(By selector, Duration duration) {
        return forElementVisible(selector, (int) duration.getSeconds(), 500);
    }

    /**
     * Checks if the element is visible on the browser
     */
    public WebElement forElementVisible(By by) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * @deprecated use method with Duration object except int
     */
    @Deprecated
    public WebElement forElementVisible(By by, int timeoutSec, int polling) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeoutSec, polling).until(
                    ExpectedConditions.visibilityOfElementLocated(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public WebElement forElementVisible(By by, Duration duration, int polling) {
        return forElementVisible(by, (int) duration.getSeconds(), polling);
    }

    /**
     * Wait for element to be either invisible or not present on the DOM.
     */
    public boolean forElementNotVisible(By by) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, DEFAULT_TIMEOUT).until(
                    ExpectedConditions.invisibilityOfElementLocated(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Wait for element to be either invisible or not present on the DOM.
     */
    public boolean forElementNotVisible(WebElement element) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, DEFAULT_TIMEOUT).until(
                    CommonExpectedConditions.invisibilityOfElementLocated(element));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forElementNotVisible(WebElement element, int timeout) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeout).until(
                    CommonExpectedConditions.invisibilityOfElementLocated(element));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Wait for element to be either invisible or not present on the DOM.
     */
    public boolean forElementNotVisible(By by, int timeout, int polling) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeout, polling).until(
                    ExpectedConditions.invisibilityOfElementLocated(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Wait for element to be either invisible or not present on the DOM.
     */
    public boolean forElementNotVisible(By by, Duration timeout) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeout.getSeconds()).until(
                    ExpectedConditions.invisibilityOfElementLocated(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Wait for element to be in viewport Either position top or left is bigger then -1
     */
    public boolean forElementInViewPort(WebElement element) {
        changeImplicitWait(250, TimeUnit.MILLISECONDS);
        try {
            return wait.until(CommonExpectedConditions.elementInViewPort(element));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forValueToBeNotPresentInElementsAttribute(
            WebElement element, String attribute, String value) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.valueToBeNotPresentInElementsAttribute(
                    element, attribute, value));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Wait for element to not be present in DOM
     */
    public boolean forElementNotPresent(By selector) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.elementNotPresent(selector));
        } finally {
            restoreDeaultImplicitWait();
        }
    }


    public boolean forValueToBePresentInElementsAttribute(
            WebElement element, String attribute, String value) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(
                    element, attribute, value));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextNotInElement(By by, String text) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.textToBeNotPresentInElement(by, text));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextInElement(By by, String text) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.textToBePresentInElement(by, text));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextInElement(By by, int index, String text) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.textToBePresentInElement(by, index, text));
        } finally {
            restoreDeaultImplicitWait();
        }
    }


    public boolean forTextInElementAfterRefresh(WebElement element, String text) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.textToBePresentInElementAfterRefresh(element, text));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextInElementAfterRefresh(By by, String text) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return sleepingWait.until(CommonExpectedConditions.textToBePresentInElementAfterRefresh(by, text));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forAttributeToContain(WebElement element, String attribute, String expectedValue) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions
                    .valueToBePresentInElementsAttribute(element, attribute,
                            expectedValue));
        } finally {
            restoreDeaultImplicitWait();
        }
    }


    public void forUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    private void restoreDeaultImplicitWait() {
        changeImplicitWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    private void changeImplicitWait(int value, TimeUnit timeUnit) {
        driver.manage().timeouts().implicitlyWait(value, timeUnit);
    }
}
