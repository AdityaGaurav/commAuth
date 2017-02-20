package com.imaginea.Utils;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mongodb.MongoClient;

public class UIUtilities {

    private WebDriver driver;
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public void setWebDriver(WebDriver driver){
        this.webDriver.set(driver);
    }

    public void clickOnElement(By locator) {
        webDriver.get().findElement(locator).click();
    }

    public void enterTextToElement(By locator, String text) {
        webDriver.get().findElement(locator).sendKeys(text);

    }

    public String getAttributeValue(By locator){
        return webDriver.get().findElement(locator).getAttribute("title");
    }

    public WebElement findElementOnPage(By locator){

        return webDriver.get().findElement(locator);
    }

    public String getCurrentPageURL(){
        return webDriver.get().getCurrentUrl();
    }

    public void navigateToURL(String URL){
        webDriver.get().get(URL);
    }

    public void scrollDownTillElement(By locator){
        JavascriptExecutor je = (JavascriptExecutor) webDriver.get();
        je.executeScript("arguments[0].scrollIntoView(true);", findElementOnPage(locator));

    }

    /**
     * Waits for the page to load
     */
    public void waitForPageToLoad() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver.get();
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            return;
        }
        // waitForPageToLoad(30);
    }

    public boolean isElementPresent(By loc, int timeOutInSeconds) {
        try {
            for (int i = 0; i < timeOutInSeconds; i++) {
                if (isElementDisplayed(loc) == true)
                    return true;
                Thread.sleep(timeOutInSeconds * 1000L);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayed(By locator){
        try {
            return webDriver.get().findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void scrollingToElementofAPage(By locator){
        WebElement element =findElementOnPage(locator);

    }

    public void waitUsingImplicitWait(){
        webDriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void waitUsingExplicitWait(By locator,int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(webDriver.get(), timeOutInSeconds);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        try{
            Thread.sleep(5000);
        }catch(Exception e){

        }

    }

    public void waitforElementVisibilityByCSS(String locator, int num) {
        WebDriverWait wait = new WebDriverWait(webDriver.get(), num);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));

    }

    public void waitforElementVisibilityByXpath(String locator, int num) {
        WebDriverWait wait = new WebDriverWait(driver, num);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

    }

    public void clickElementByXpath(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public void waitforElementClickableByCSS(String locator, int num) {
        WebDriverWait wait = new WebDriverWait(driver, num);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));

    }

    public String getText(String locator) {
        return driver.findElement(By.cssSelector(locator)).getText();
    }

    public void waitForURLToBe(String url) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void pageRefresh() {
        driver.navigate().refresh();
    }

//    public void clickOnHomePage() {
//        clickOnElement(".dashBoard__logo___3GwRS");
//    }


}
