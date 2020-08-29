package com.scratch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class HomePage {
    WebDriver driver = new ChromeDriver();
    String URL = "https://scratch.mit.edu/";

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite");
        driver.get(URL);
        driver.manage().window().maximize();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void testTitle(){
        System.out.println("testTitle");
        Assert.assertEquals(driver.getTitle(), "Scratch - Imagine, Program, Share");
    }

    @Test
    public void testCurrentUrl(){

        System.out.println("testCurrentUrl");
        //driver.get("https://scratch.mit.edu/");
        //driver.manage().window().maximize();
        Assert.assertEquals(driver.getCurrentUrl(), "https://scratch.mit.edu/");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("afterSuite");
        driver.quit();
    }
}
