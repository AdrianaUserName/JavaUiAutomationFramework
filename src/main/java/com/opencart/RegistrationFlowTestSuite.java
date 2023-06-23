package com.opencart;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.AccountCreatedPage;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class RegistrationFlowTestSuite {
WebDriver driver;
HomePage homePage;
RegisterPage registerPage;
    static int counter = 0;
    @BeforeEach
    public void executeTheCodeBeforeEachTestFromThisClass(){
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://andreisecuqa.host/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        counter++;
        System.out.println("The Test number " +counter +" started");
    }

    @Test
    @DisplayName("The url contains success keyword after registration with valid data")
    public void registerFlowRedirectsTheUserToTheCorrectUrl() throws InterruptedException {

        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String email = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(21,22);

        registerPage.fillInTheRegisterForm(firstName , lastName , email, password);
        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();

        Thread.sleep(1000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("/index.php?route=account/success&language");
        String errorMessage = "The URL  " + driver.getCurrentUrl() + " contains success keyword";
        Assertions.assertTrue(urlContainsTheCorrectKeyWords, errorMessage);

      }

    @Test
    @DisplayName("The url contains register keyword when privacy policy is not accepted ")
    public void registerFlowIsBlockedByPrivacyPolicyToogleThatIsNotAccepted() throws InterruptedException {

        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String email = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(21,22);

        registerPage.fillInTheRegisterForm(firstName , lastName , email, password);
       // Do not enable the Privacy Toggle
      //  registerPage.switchOnThePrivacyToggle(driver);

        registerPage.clickOnContinueButton();
        Thread.sleep(1000);

        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("/index.php?route=account/success&language=en-gb&customer_token=6e1d5286e4b147fe4e523e86e4");
        String errorMessage = "The URL  " +driver.getCurrentUrl() + " does not contains success keyword";
        Assertions.assertFalse(urlContainsTheCorrectKeyWords, errorMessage);

        boolean urlContainsRegisterKeyword = driver.getCurrentUrl().contains("regist");
        Assertions.assertTrue(urlContainsRegisterKeyword, "The URL belongs to register page");
  }

    @AfterEach
    public void executeThisMethodAfterEachTestCase(){
        DriverManager.getInstance().quitTheDriver();
        System.out.println("The Test number " +counter + " was finished");

    }
}
