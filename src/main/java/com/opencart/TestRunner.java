package com.opencart;

import com.opencart.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {


        //Define a driver object that will be used for future actions.
        WebDriver driver = DriverManager.getInstance().getDriver();


        driver.get("https://mvnrepository.com/");

        String currentWindowName =driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://maven.apache.org/download.cgi");

        driver.close();

        driver.switchTo().window(currentWindowName);

        driver.get("https://www.google.com/");

        driver.quit();

        System.out.println("The execution was finished");
    }
}
