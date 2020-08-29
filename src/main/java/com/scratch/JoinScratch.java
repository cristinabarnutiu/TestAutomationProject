package com.scratch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class JoinScratch {

    static final String URL = "https://scratch.mit.edu/";
    String testuser = "user20078212";
    String testpassword = "password20078212";

    //System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();

    public void waitForThreeSeconds(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void beforeJoinScratch() {
        //System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        //WebDriver driver = new ChromeDriver();
        System.out.println("executing beforeJoinScratch");
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterJoinScratch() {
        System.out.println("executing afterJoinScratch");
        driver.quit();
    }

    @Test (priority = 0)
    public void testJoinScratchUserDetails() {
        System.out.println("executing testJoinScratchUserDetails");

        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
       //WebDriver driver = new ChromeDriver();
        //driver.manage().window().maximize();

        //driver.get(URL);

        //Click Join Scratch Button
        WebElement joinScratchLink = driver.findElement(By.cssSelector("a.registrationLink"));
        joinScratchLink.click();

        //verify Landing on Registration Page
        String joinPageTitle = driver.getTitle();
        Assert.assertEquals(joinPageTitle, "Scratch - Join Scratch");

        WebElement joinModalTitle = driver.findElement(By.cssSelector("div.modal-title.join-flow-title"));
        Assert.assertEquals(joinModalTitle.getText(), "Join Scratch");

        WebElement joinModalDescription = driver.findElement(By.cssSelector("div.join-flow-description"));
        Assert.assertEquals(joinModalDescription.getText(), "Create projects, share ideas, make friends. It’s free!");

        WebElement usernameLabel = driver.findElement(By.cssSelector("div.join-flow-input-title"));
        Assert.assertEquals(usernameLabel.getText(), "Create a username");

        WebElement usernameInput = driver.findElement(By.cssSelector("input#username.formik-input.join-flow-input"));
        usernameInput.sendKeys(testuser);

        WebElement passwordLabel = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/form/div/div[1]/div/div[3]/div[3]/div[1]"));
        Assert.assertEquals(passwordLabel.getText(), "Create a password");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(testpassword);
        WebElement passwordConfirmInput = driver.findElement(By.id("passwordConfirm"));
        passwordConfirmInput.sendKeys(testpassword);

        WebElement showPasswordCheckboxLabel = driver.findElement(By.cssSelector("label.formik-checkbox-label.formik-label.join-flow-input-title"));
        Assert.assertEquals(showPasswordCheckboxLabel.getText(), "Show password");
        WebElement showPasswordCheckbox = driver.findElement(By.name("showPassword"));
        Assert.assertTrue(showPasswordCheckbox.isSelected());

        //wait for 3 seconds
        waitForThreeSeconds();

        WebElement nextButton = driver.findElement(By.cssSelector("div.modal-title.next-step-title"));
        nextButton.click();
        Assert.assertEquals(driver.getTitle(),"Scratch - Join Scratch");
    }

    @Test (dependsOnMethods = "testJoinScratchUserDetails", priority=1)
    public void testJoinScratchCountry() {
        System.out.println("executing testJoinScratchCountry");
        //System.out.println(driver.getTitle());

        Assert.assertEquals(driver.getTitle(),"Scratch - Join Scratch");

        WebElement joinCountryTitle = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/form/div/div[2]/div/div[1]"));
        Assert.assertEquals(joinCountryTitle.getText(),"What country do you live in?");

        WebElement joinSelectCountryDropDown = driver.findElement(By.tagName("select"));
        joinSelectCountryDropDown.click();
        Select dropdown = new Select(joinSelectCountryDropDown);
        dropdown.selectByValue("Romania");

        //wait for 3 seconds
        waitForThreeSeconds();

        WebElement nextButton = driver.findElement(By.cssSelector("div.modal-title.next-step-title"));
        nextButton.click();

    }

    @Test (dependsOnMethods = "testJoinScratchCountry", priority=2)
    public void testJoinScratchDateOfBirth() {
        System.out.println("executing testJoinScratchDateOfBirth");

        WebElement joinDateOfBirthTitle = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/form/div/div[2]/div/div[1]"));
        Assert.assertEquals(joinDateOfBirthTitle.getText(),"When were you born?");

        //select birth month
        WebElement joinDateOfBirthBirthMonth = driver.findElement(By.id("birth_month"));
        joinDateOfBirthBirthMonth.click();
        Select birthMonth = new Select(joinDateOfBirthBirthMonth);
        birthMonth.selectByValue("1");

        //select birth year
        WebElement joinDateOfBirthBirthYear = driver.findElement(By.id("birth_year"));
        joinDateOfBirthBirthYear.click();
        Select birthYear = new Select(joinDateOfBirthBirthYear);
        birthYear.selectByValue("2000");

        waitForThreeSeconds();
        WebElement nextButton = driver.findElement(By.cssSelector("div.modal-title.next-step-title"));
        nextButton.click();

    }

    @Test (dependsOnMethods = "testJoinScratchDateOfBirth", priority=3)
    public void testJoinScratchGender() {
        System.out.println("executing testJoinScratchGender");

        WebElement joinGenderTitle = driver.findElement(By.cssSelector("div.modal-title.join-flow-title"));
        Assert.assertEquals(joinGenderTitle.getText(),"What's your gender?");

        WebElement joinGenderDescription = driver.findElement(By.cssSelector("div.join-flow-description.join-flow-gender-description"));
        Assert.assertEquals(joinGenderDescription.getText(),"Scratch welcomes people of all genders.");

        WebElement joinGenderOptionPreferNot = driver.findElement(By.id("GenderRadioOptionPreferNot"));
        joinGenderOptionPreferNot.click();

        waitForThreeSeconds();

        WebElement nextButton = driver.findElement(By.cssSelector("div.modal-title.next-step-title"));
        nextButton.click();
    }

    @Test (dependsOnMethods = "testJoinScratchGender", priority=4)
    public void testJoinScratchEmail() {
        System.out.println("executing testJoinScratchEmail");

        WebElement joinEmailTitle = driver.findElement(By.cssSelector("div.modal-title.join-flow-title.join-flow-email-title"));
        Assert.assertEquals(joinEmailTitle.getText(),"What's your email?");

        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys(testuser+"@email.com");

        WebElement termsOfUseAndPrivacyPolicy = driver.findElement(By.className("join-flow-footer-message"));
        Assert.assertTrue(termsOfUseAndPrivacyPolicy.isDisplayed());

        waitForThreeSeconds();

        WebElement nextButton = driver.findElement(By.cssSelector("div.modal-title.next-step-title"));
        nextButton.click();
    }


}
