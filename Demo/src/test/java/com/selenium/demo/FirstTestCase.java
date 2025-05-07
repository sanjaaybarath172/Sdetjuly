package com.selenium.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FirstTestCase {
    public static void main(String[] args) {

        WebDriver driver=new ChromeDriver();
        System.out.println("testing");
        driver.get("https://www.opencart.com/index.php?route=cms/demo");
        System.out.println(driver.getTitle());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.close();
        driver.quit();
    }
}