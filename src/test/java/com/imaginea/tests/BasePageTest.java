package com.imaginea.tests;

import com.imaginea.pageObjects.HomePage;
import com.imaginea.pageObjects.LoginPage;
import com.imaginea.pageObjects.MobileSearchPage;
import com.imaginea.setUp.BaseClass;

/**
 * Created by adityag on 2/17/17.
 */
public class BasePageTest extends BaseClass {
    protected HomePage homePage = new HomePage();
    protected LoginPage loginPage = new LoginPage();

    protected MobileSearchPage mobileSerachPage = new MobileSearchPage();

}
