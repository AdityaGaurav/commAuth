package com.imaginea.tests;


import org.testng.annotations.Test;

/**
 * Created by adityag on 2/17/17.
 */
public class LoginPageTest extends BasePageTest {


    @Test
    public void loginIntoApplication() {
        loginPage.doLogin();
    }


}
