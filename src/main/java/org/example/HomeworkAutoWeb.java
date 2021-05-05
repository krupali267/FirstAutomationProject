package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class HomeworkAutoWeb
{
    protected static WebDriver driver;
    @BeforeMethod
            public void openBrowser()
    {
        // timestamp for current time - to create unique value each time we run the program
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // setting up chromedriver path from your system
        System.setProperty("webdriver.chrome.driver","src\\test\\Resources\\Browser\\chromedriver.exe");
        // initialzing the object
        driver = new ChromeDriver();
        // maximize the browser window
        driver.manage().window().maximize();
        // applying implicity wait to driver object
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // open url
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void userRegisterSuccessfully()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // click on Register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // select the gender
        driver.findElement(By.xpath("//input[@id='gender-male']")).click();
        // type Firstname
        driver.findElement(By.id("FirstName")).sendKeys("Ajay");
        // type Lastname
        driver.findElement(By.id("LastName")).sendKeys("Patel");

        // select date of birthdate
        Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectDay.selectByVisibleText("26");
        // select month of birth
        Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        selectMonth.selectByValue("2");
        // select year of birth
        Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        selectYear.selectByIndex(4);
        // type email with timestamp
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("testtest"+timestamp.getTime()+"@test.com");
        // type company name
        driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("XYZ Limited");
       // click on the checkbox of Newsletter
        driver.findElement(By.xpath("//input[@type = 'checkbox']")).click();
        // type password
        driver.findElement(By.id("Password")).sendKeys("testtest");
        // retype password
        driver.findElement(By.id("ConfirmPassword")).sendKeys("testtest");
        // click on Register button
        driver.findElement(By.name("register-button")).click();

    }
    @Test
    public void verifyAppleMacPrice() // program to verify price of Apple Macbook
    {
        // variable for expected price of Apple Mac
        String priceExpected = "$2,000.00";
        // initializing variable with the actual value the driver finds
        String actualPrice = driver.findElement(By.xpath("(//span [@ class='price actual-price'])[2]")).getText();
        // checkpoint for matching expected and actual price
        Assert.assertEquals(actualPrice,priceExpected,"Test has failed");

    }

@Test
    public void compareTwoProducts() // program to compare two products
{
    // click to add Apple Mac in compare list
    driver.findElement(By.xpath("(//button[@class='button-2 add-to-compare-list-button'])[2]")).click();
    try {
        Thread.sleep(6000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    // click to add HTC mobile in compare list
    driver.findElement(By.xpath("(//button[@class='button-2 add-to-compare-list-button'])[3]")).click();
    // click on compare products link
    driver.findElement(By.xpath("//p[@class='content']//a[@href='/compareproducts']")).click();

    // assertion point to verify if products are added to compare list and displays correctly
    boolean productsDisplayed = driver.findElement(By.xpath("//div[@class='page-body']")).isDisplayed();
     Assert.assertTrue(productsDisplayed,"Products are displayed");

    try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    // click to clear products from compare products list
    driver.findElement(By.xpath("//a[@href= '#']")).click();

    // assertion point to verify no items are in compare list and right message is displayed
    String textDisplayed = "You have no items to compare.";
    String productsUndisplayed = driver.findElement(By.xpath("//div[@class='no-data']")).getText();
    Assert.assertEquals(productsUndisplayed,textDisplayed,"Products are not displayed");
}
@Test
    public void buildOwnComputer() // program for build your own computer option
{
    // click on build your own computer on homepage
    driver.findElement(By.xpath("(//a[@href='/build-your-own-computer'])[2]")).click();
    // select the processor
    Select selectProcessor = new Select(driver.findElement(By.name("product_attribute_1")));
    selectProcessor.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");
    // select the RAM for computer
    Select selectRam = new Select(driver.findElement(By.name("product_attribute_2")));
    selectRam.selectByIndex(2);
    // click to select type of HDD
    driver.findElement(By.id("product_attribute_3_6")).click();
    // click to select type of OS
    driver.findElement(By.id("product_attribute_4_9")).click();
    // click to select the software
    driver.findElement(By.id("product_attribute_5_10")).click();
    try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    // click on add to cart button
    driver.findElement(By.id("add-to-cart-button-1")).click();
    try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    // click on Shopping Cart
    driver.findElement(By.xpath("//a[@href='/cart']")).click();
    // assertion point to verify the product with required specifications is added to shopping basket
    boolean detailsDisplayed = driver.findElement(By.linkText("Shopping cart")).isDisplayed();
    Assert.assertTrue(detailsDisplayed,"Product details are displayed");

}
@AfterMethod
    public void browserClose() // program to close the browser
{
    // close the browser
        driver.quit();
}

}
