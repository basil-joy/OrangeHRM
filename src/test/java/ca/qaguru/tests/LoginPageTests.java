package ca.qaguru.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTests {
    WebDriver driver;
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
    @Test
    public void validLogin(){
        driver.findElement(By.xpath("//*[@id='txtUsername']"))
                .sendKeys("admin");
        driver.findElement(By.xpath("//*[@id='txtPassword']"))
                .sendKeys("admin123");
        driver.findElement(By.xpath("//*[@id='btnLogin']")).click();

        driver.findElement(By.xpath("//*[@id='welcome']"));
    }
    @Test
    public void invalidLogin(){
        driver.findElement(By.xpath("//*[@id='txtUsername']"))
                .sendKeys("admin");
        driver.findElement(By.xpath("//*[@id='txtPassword']"))
                .sendKeys("admin12312323");
        driver.findElement(By.xpath("//*[@id='btnLogin']")).click();

        String msg = driver.findElement(By.xpath("//*[@id='spanMessage']")).getText();
        Assert.assertEquals(msg, "Invalid credentials");
    }

    @Test(enabled = false)
    public void exceptionHandling(){

        try {

            driver.findElement(By.xpath("//*[@id='txtUsername']"));
//            float no = 3/0;

        }
        catch (ArithmeticException exception){
            System.out.println("Arithmetic  Exception");
        }catch (NoSuchElementException exception){
            System.out.println("No such element exception");
        }catch (Exception exception){
            System.out.println("Some exception occured...");
        }finally {
            System.out.println("I am finally block");
        }

        System.out.println("The end of test case");

    }
}
