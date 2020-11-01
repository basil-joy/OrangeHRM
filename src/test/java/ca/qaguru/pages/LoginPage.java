package ca.qaguru.pages;

import ca.qaguru.lib.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends PageBase {
    private String txtUsername = "//*[@id='txtUsername']";
    private String txtPassword = "//*[@id='txtPassword']";
    private String btnLogin = "//*[@id='btnLogin']";
    private String lblError = "//*[@id='spanMessage']";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HeaderPage login(String username, String password){
        setText(By.xpath(txtUsername),username);
        setText(By.xpath(txtPassword),password);
        click(By.xpath(btnLogin));
        return new HeaderPage(driver);
    }
    public void invalidLogin(String username, String password,String errMsg){
        setText(By.xpath(txtUsername),username);
        setText(By.xpath(txtPassword),password);
        click(By.xpath(btnLogin));
        Assert.assertTrue(isElementPresent(By.xpath(lblError)),"Error not displayed");
        String actError = getText(By.xpath(lblError));
        Assert.assertEquals(actError,errMsg,"Incorrect error message");

    }

}
