package ca.qaguru.lib;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    protected WebDriver driver;
    public static final String BASE_URL = "https://opensource-demo.orangehrmlive.com";
    @BeforeSuite
    public void beforeSuite(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void clear(){
        driver.quit();
    }
}
