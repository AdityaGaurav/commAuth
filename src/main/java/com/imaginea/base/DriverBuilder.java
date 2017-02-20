package com.imaginea.base;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverBuilder {


    private WebDriver driver;
    private String macDriversPath = System.getProperty("user.dir") + File.separator +
            "WebDrivers" + File.separator + "MacDrivers";
    private String windowsDriversPath = System.getProperty("user.dir") + File.separator +
            "WebDrivers" + File.separator + "WindowsDrivers";
    private String osPlatform = System.getProperty("os.name");


    public WebDriver getDriver(String browserName) {
        DesiredCapabilities capability = new DesiredCapabilities();
        if (browserName.equalsIgnoreCase("chrome")) {
            System.out.println(osPlatform);
            if (osPlatform.toLowerCase().contains("mac")) {
                System.setProperty("webdriver.chrome.driver",
                        macDriversPath + File.separator + "chromedriver");
                capability = DesiredCapabilities.chrome();
                driver = new ChromeDriver(capability);
            } else if (osPlatform.toLowerCase().contains("windows")) {
                System.setProperty("webdriver.chrome.driver",
                        windowsDriversPath + File.separator + "chromedriver");
                capability = DesiredCapabilities.chrome();
                driver = new ChromeDriver(capability);

            } else {
                System.out.println("Os Platform is not listed here. Please add your details here. ");
            }

        }
        if (browserName.equalsIgnoreCase("ie")) {

            System.setProperty("webdriver.ie.dirver",
                    windowsDriversPath + File.separator + "IEDriverServer.exe");
            capability = DesiredCapabilities.internetExplorer();
            driver = new InternetExplorerDriver(capability);

        }

        if (browserName.equalsIgnoreCase("FireFox")) {

            if (osPlatform.toLowerCase().contains("mac")) {
                System.setProperty("webdriver.gecko.driver",
                        macDriversPath + File.separator + "geckodriver");
                capability = DesiredCapabilities.firefox();
                driver = new FirefoxDriver(capability);
            } else if (osPlatform.toLowerCase().contains("windows")) {
                System.setProperty("webdriver.gecko.driver",
                        windowsDriversPath + File.separator + "geckodriver");
                capability = DesiredCapabilities.firefox();
                driver = new FirefoxDriver(capability);

            } else {
                System.out.println("Os Platform is not listed here. Please add your details here. ");
            }

        }
        return driver;
    }

}
