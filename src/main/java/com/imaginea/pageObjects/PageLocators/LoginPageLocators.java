package com.imaginea.pageObjects.PageLocators;

import org.openqa.selenium.By;

/**
 * Created by adityag on 2/17/17.
 */
public class LoginPageLocators {

    public static By LOGIN_LINK = By.xpath(".//*[@id='container']/div/header/div[1]/div[1]/div/ul/li[9]/a");
    public static By MOBILE_NUMBER = By.xpath("html/body/div[3]/div/div/div/div/div[2]/div/form/div[1]/input");
    public static By PASSWORD = By.xpath("html/body/div[3]/div/div/div/div/div[2]/div/form/div[2]/input");
    public static By LOGIN_BUTTON = By.xpath("html/body/div[3]/div/div/div/div/div[2]/div/form/div[3]/button");
}
