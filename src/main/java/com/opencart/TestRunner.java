package com.opencart;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import com.opencart.managers.ScrollManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {


        //Define a driver object that will be used for future actions.
        WebDriver driver = DriverManager.getInstance().getDriver();


        driver.get("https://www.google.com/");

        String currentWindowName = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://andreisecuqa.host/");

        WebElement accountIcon = driver.findElement(By.xpath("//i[@class='fa-solid fa-user']"));
        accountIcon.click();

        WebElement registerButton = driver.findElement(By.xpath("//a[@class='dropdown-item'][normalize-space()='Register']"));
        registerButton.click();

        String firstName = DataFakerManager.getRandomName();
        System.out.println("The generated first name is : " + firstName);

        String lastName = DataFakerManager.getRandomName();
        System.out.println("The generated last name is : " + lastName);

        String email = DataFakerManager.getRandomEmail();
        System.out.println("The generated email is : " + email);

        String password = DataFakerManager.getRandomPassword(21,22);
        System.out.println("The generated password is : " + password);

        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
        lastNameInput.sendKeys(lastName);

        WebElement emailInput  = driver.findElement(By.id("input-email"));
        emailInput.sendKeys(email);

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys(password);

        WebElement privacyCheckBox = driver.findElement(By.xpath("//input[@name='agree']"));
        ScrollManager.scrollToElement(driver,privacyCheckBox);
        privacyCheckBox.click();

        WebElement continueButton = driver.findElement(By.xpath("//button[normalize-space()='Continue']"));
        continueButton.click();

        Thread.sleep(5000);
        System.out.println(driver.getCurrentUrl());

        driver.close();

        driver.switchTo().window(currentWindowName);

        driver.get("https://andreisecuqa.host/");

        driver.quit();

        System.out.println("The execution was finished");
    }
}
