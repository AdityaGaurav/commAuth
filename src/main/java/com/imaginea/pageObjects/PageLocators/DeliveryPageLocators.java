package com.imaginea.pageObjects.PageLocators;

import org.openqa.selenium.By;

/**
 * Created by adityag on 2/19/17.
 */
public class DeliveryPageLocators {

    public static final By PLACE_ORDER_LOCATOR = By.xpath(".//*[@id='view-cart-form']/button");
    public static final By DELIVERY_HERE_BUTTON = By.xpath(".//*[@id='ng-app']//div[@class='address_detail ng-scope'][2]/a/p[@class='select_btn btn btn-white']");
    public static final By CONTINUE_BUTTON = By.xpath(".//*[@id='order_summary_panel']//a[@bind-log='order continue']");
}
