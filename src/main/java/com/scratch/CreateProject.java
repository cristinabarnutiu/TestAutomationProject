package com.scratch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateProject {
    WebDriver driver = new ChromeDriver();
    String URL = "https://scratch.mit.edu/";

    @BeforeMethod
    public void beforeCreateProject() {
        System.out.println("executing beforeCreateProject");
        driver.get(URL);
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void afterCreateProject() {
        System.out.println("executing afterCreateProject");
        driver.quit();
    }

    @Test(priority = 0)
    public void testCreateProject() {
        System.out.println("executing testCreateProject");
        WebElement createLink = driver.findElement(By.cssSelector("li.link.create"));
        createLink.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //check if current url is create project page
        Assert.assertEquals(driver.getCurrentUrl(),"https://scratch.mit.edu/projects/editor/?tutorial=getStarted");
    }
}
