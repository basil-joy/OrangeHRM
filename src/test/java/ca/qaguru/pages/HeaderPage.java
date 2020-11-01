package ca.qaguru.pages;

import ca.qaguru.lib.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HeaderPage extends PageBase {
    private String lblWelcome = "//*[@id='welcome']";

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void assertWelcomeMessage(){
        Assert.assertTrue(isElementVisible(By.xpath(lblWelcome)),"Login not successful");
    }

}
