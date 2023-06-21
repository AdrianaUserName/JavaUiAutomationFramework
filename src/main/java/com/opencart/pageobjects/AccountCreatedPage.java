package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;

public class AccountCreatedPage extends Page{
    public AccountCreatedPage(WebDriver driver){
        super(driver);

    }
   @FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Logout']")
    private WebElement logOutOption;

    public void logOutFromTheAccount(){
        accountIcon.click();
        logOutOption.click();
        System.out.println("The user is logged out ");
    }

}
