package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class HomePageSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    HomePage homePage = new HomePage(driver);
    @Given(": Home Page is accessed")
    public void homePageIsAccessed() {
        DriverManager.getInstance().getDriver().get("https://andreisecuqa.host/");
    }

    @And("RegisteredPage is accessed from HomePage menu")
    public void registeredPageIsAccessedFromHomePageMenu() {
        homePage.navigateToRegisterPageFromHeaderMenu();
    }
}
