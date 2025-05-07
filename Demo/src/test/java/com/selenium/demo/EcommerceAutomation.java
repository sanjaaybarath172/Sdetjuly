package com.selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class EcommerceAutomation {

    WebDriver driver;

    // Set up WebDriver and open the browser
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe"); // Update path here

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run in headless mode (optional)
        driver = new ChromeDriver(options);driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Test: Amazon Search and Add to Cart
    @Test
    public void testAmazonAddToCart() {
        driver.get("https://www.amazon.in");

        // Searching for a product (Laptop)
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Laptop");
        searchBox.submit();

        // Wait for the product to be displayed and click on the first product
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".s-main-slot .s-result-item")));
        product.click();

        // Add to cart
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        // Go to Cart and verify item is added
        WebElement cartIcon = driver.findElement(By.id("nav-cart"));
        cartIcon.click();
        WebElement cartItem = driver.findElement(By.cssSelector(".sc-list-item-content"));
        Assert.assertTrue(cartItem.isDisplayed(), "Item is not added to the cart!");

        System.out.println("Amazon add to cart test passed!");
    }

    // Test: Flipkart Search and Add to Cart
    @Test
    public void testFlipkartAddToCart() {
        driver.get("https://www.flipkart.com");

        // Close the login pop-up
        WebElement closeButton = driver.findElement(By.cssSelector("button._2KpZ6l"));
        closeButton.click();

        // Searching for a product (Smartphone)
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Smartphone");
        searchBox.submit();

        // Wait for the product to be displayed and click on the first product
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("._1AtVbE ._4rR01T")));
        product.click();

        // Add to cart
        WebElement addToCartButton = driver.findElement(By.xpath("//button[text()='ADD TO CART']"));
        addToCartButton.click();

        // Go to Cart and verify item is added
        WebElement cartIcon = driver.findElement(By.xpath("//a[@href='/viewcart']"));
        cartIcon.click();
        WebElement cartItem = driver.findElement(By.cssSelector(".sc-list-item-content"));
        Assert.assertTrue(cartItem.isDisplayed(), "Item is not added to the cart!");

        System.out.println("Flipkart add to cart test passed!");
    }

    // Test: Handling Alerts (Pop-ups) (Example on Amazon)
    @Test
    public void testHandleAlert() {
        driver.get("https://www.amazon.in");

        // Try clicking on an element that triggers an alert (this is just an example)
        WebElement alertButton = driver.findElement(By.xpath("//button[contains(text(),'Some Button')]"));
        alertButton.click();

        // Handle the alert if it appears
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();  // Accept the alert
            System.out.println("Alert handled successfully");
        } catch (Exception e) {
            System.out.println("No alert appeared");
        }
    }

    // Test: Logging into Amazon
    @Test
    public void testAmazonLogin() {
        driver.get("https://www.amazon.in");

        // Click on the login button
        WebElement loginButton = driver.findElement(By.id("nav-link-accountList"));
        loginButton.click();

        // Enter email
        WebElement emailField = driver.findElement(By.id("ap_email"));
        emailField.sendKeys("your-email@example.com");

        // Submit the email
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        // Enter password
        WebElement passwordField = driver.findElement(By.id("ap_password"));
        passwordField.sendKeys("your-password");

        // Submit login form
        WebElement signInButton = driver.findElement(By.id("signInSubmit"));
        signInButton.click();

        System.out.println("Amazon login test completed.");
    }

    // Tear down WebDriver
    @BeforeMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Run all tests as a batch using TestNG
    public static void main(String[] args) {
        EcommerceAutomation automation = new EcommerceAutomation();
        automation.setUp();

        // Run tests
        automation.testAmazonAddToCart();
        automation.testFlipkartAddToCart();
        automation.testHandleAlert();
        automation.testAmazonLogin();

        automation.tearDown();
    }
}
