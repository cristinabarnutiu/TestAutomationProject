package com.FindBrokenLink;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindBrokenLink {

    @Test
    public void testFindBrokenLinks(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://scratch.mit.edu/");
        driver.manage().window().maximize();

        //Apply Implicit Wait - wait for 30 seconds
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total number of links on the current page is "+links.size());

        for (WebElement link : links){
            //System.out.println(link.getText());
            String URL = link.getAttribute("href");
            GetLinkStatus.verifyLink(URL);
        }

        //Display number of broken links
        System.out.println("Number of broken links on the current page: ");
        GetLinkStatus.getInvalidLinkCount();

        driver.quit();

    }
}
