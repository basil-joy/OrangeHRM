package ca.qaguru.pages;

import ca.qaguru.lib.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class HeaderPage extends PageBase {
    private String lblWelcome = "//*[@id='welcome']";
    private String mnuItem = "//*[@id='mainMenu']//descendant::*[text()='XXX']";

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void assertWelcomeMessage(){
        Assert.assertTrue(isElementVisible(By.xpath(lblWelcome)),"Login not successful");
    }
    public void selectMenu(String menu){//Admin|Job|Job Title
        List<String> menuItems = Arrays.asList(menu.split("\\|"));
        for (String item:menuItems){
            clickMenu(item);
        }
    }

    private void clickMenu(String item) {
        click(By.xpath(mnuItem.replace("XXX",item)));
    }

}
