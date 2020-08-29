package com.scratch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class WatchVideo {
    WebDriver driver = new ChromeDriver();
    String URL = "https://scratch.mit.edu/";

    @BeforeMethod
    public void beforeWatchVideo() {
        System.out.println("executing beforeWatchVideo");
        driver.get(URL);
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void afterWatchVideo() {
        System.out.println("executing afterWatchVideo");
        driver.quit();
    }

    @Test(priority = 0)
    public void testWatchVideo() {
        System.out.println("executing testWatchVideo");
        WebElement watchVideoButton = driver.findElement(By.cssSelector("div.watch-button.button"));
        watchVideoButton.click();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement video = driver.findElement(By.xpath("//*[@id=\"view\"]/div/div[1]/div[1]/div[2]/div/iframe"));
        //System.out.println(video.getAttribute("src"));
        String videoSource = video.getAttribute("src");
        Assert.assertTrue(videoSource.equalsIgnoreCase("https://fast.wistia.net/embed/iframe/joal01i8b1?seo=false&videoFoam=true"));
    }
}

