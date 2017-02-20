package com.imaginea.tests;

import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.imaginea.pageObjects.HomePage;

import com.imaginea.setUp.BaseClass;

public class HomePageTests extends BasePageTest{

    @Test
    public void searchForMobile() throws InterruptedException{
        homePage.searchForMobile();
    }
  
}
