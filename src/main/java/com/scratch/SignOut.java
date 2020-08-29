package com.scratch;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class SignOut {
    static final String URL = "https://scratch.mit.edu/";
    public WebDriver driver = null;

    //Test Data
    String validUser = "username_testauto";
    String invalidUser = "invalid";
    String validPassword = "password_testauto";
    String invalidPassword = "invalid";

    public void waitFor(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserLoggedIn(){
        WebElement signInLink = driver.findElement(By.cssSelector("li.link.right.login-item"));
        return (!signInLink.isDisplayed());
    }

    @BeforeMethod
    public void beforeSignOut() {

        System.out.println("executing beforeSignOut");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();

        WebElement signInLink = driver.findElement(By.cssSelector("li.link.right.login-item"));
        signInLink.click();

        waitFor(3000);

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement signInButton = driver.findElement(By.cssSelector("button.button.submit-button.white"));

        usernameInput.sendKeys(validUser);
        passwordInput.sendKeys(validPassword);
        waitFor(2000);
        signInButton.click();

        waitFor(3000);

    }

    @AfterMethod
    public void afterSignOut() {
        System.out.println("executing afterSignOut");
        driver.quit();
    }

    @Test
    public void testSignOut(){
        System.out.println("executing afterSignOut");
        WebElement profileName = driver.findElement(By.className("profile-name"));
        Assert.assertTrue(profileName.isDisplayed());
        profileName.click();

        waitFor(3000);

        WebElement signOutLink = driver.findElement(By.linkText("Sign out"));
        signOutLink.click();

        waitFor(3000);

        Assert.assertFalse(isUserLoggedIn());
    }

}
