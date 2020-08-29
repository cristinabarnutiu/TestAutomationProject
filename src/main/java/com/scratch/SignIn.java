package com.scratch;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class SignIn {
    static final String URL = "https://scratch.mit.edu/";
    //System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
    public WebDriver driver = null;

    //Test Data
    String validUser = "username_testauto";
    String invalidUser = "invalid";
    String validPassword = "password_testauto";
    String invalidPassword = "invalid";


    public static void waitFor(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void login(String username, String password){

        WebElement signInLink = driver.findElement(By.cssSelector("li.link.right.login-item"));
        signInLink.click();

        waitFor(3000);

        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement signInButton = driver.findElement(By.cssSelector("button.button.submit-button.white"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        waitFor(2000);
        signInButton.click();

        waitFor(3000);
    }


    @BeforeMethod
    public void beforeSignIn() {
        //System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");

        System.out.println("executing beforeSignIn");

        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterSignIn() {
        System.out.println("executing afterSignIn");
        driver.quit();
    }

    //positive test
    @Test(priority=0)
    public void testSignInWithValidCredentials() {
        System.out.println("executing testSignInWithValidCredentials");
        login(validUser, validPassword);

        WebElement profileName = driver.findElement(By.className("profile-name"));
        Assert.assertTrue(profileName.isDisplayed());
    }

    //negative test
    @Test (priority=1)
    public void testSignInWithInvalidCredentials() {
        System.out.println("executing testSignInWithInvalidCredentials");
        login(invalidUser, invalidPassword);

        WebElement errorMessage = driver.findElement(By.className("error"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void testSingInNeedHelp(){
        System.out.println("executing testSignInNeedHelp");

        WebElement signInLink = driver.findElement(By.cssSelector("li.link.right.login-item"));
        signInLink.click();

        waitFor(3000);

        WebElement needHelpLink = driver.findElement(By.linkText("Need Help?"));
        needHelpLink.click();
        waitFor(2000);

        String passwordResetURL = driver.getCurrentUrl();
        Assert.assertEquals(passwordResetURL, "https://scratch.mit.edu/accounts/password_reset/");
    }
}
