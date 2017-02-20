package com.imaginea.setUp;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.imaginea.Utils.UIUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import com.imaginea.base.DriverBuilder;

public class BaseClass {

    private WebDriver driver;
    protected UIUtilities util;

    public BaseClass(){
         this.util= new UIUtilities();

    }



    // ThreadLocal will keep local copy of driver
//    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();


    //  WebDriver driver = null;


    @Parameters({"browserName","URL"})
    @BeforeClass
    public void beforeClass(String browserName, String URL) throws MalformedURLException {
        // setting webdriver
        this.driver = new DriverBuilder().getDriver(browserName);
        util.setWebDriver(this.driver);
        System.out.println("Launching Browser:" + browserName);
        this.driver.get(URL);
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        this.driver.close();
        this.driver.quit();
        //dr.set(null);
    }
    static Map<String, RemoteWebDriver> map = new HashMap<>();
    public static Map<String, RemoteWebDriver> getDriverInstance() {
        return map;
    }

}
