package com.selenium.basic;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SampleSeleniumTest {

    @Test
    public void verifyHomePage(){
        String URL = "https://scratch.mit.edu/";

        //Open the URL with Selenium

        System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

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
