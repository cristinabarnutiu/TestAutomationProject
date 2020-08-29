package com.selenium.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvokeOtherBrowsers {

    @Test
    public void verifyHomePage(){
        String URL = "https://scratch.mit.edu/";

        //Open the URL with Selenium

        System.setProperty("webdriver.gecko.driver","drivers\\geckodriver.exe");

        WebDriver driver = new FirefoxDriver();

        //To maximize the browser
        driver.manage().window().maximize();

        driver.get(URL);

        //To maximize the browser
        driver.manage().window().maximize();

        //Verify HomePage Title
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"Scratch - Imagine, Program, Share");

        //to close the browser
        driver.close();

    }
}

