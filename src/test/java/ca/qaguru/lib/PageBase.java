package ca.qaguru.lib;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
    protected WebDriver driver;
    private final long WAIT_TIME = 5L;
    private static int counter = 0;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(By by) {
        try {
            new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by))
                    .click();
        }catch (StaleElementReferenceException exception) {
            sleep(100);
            click(by);
        }catch (ElementNotInteractableException exception) {
            sleep(100);
            click(by);
        }
    }

    protected void setText(By by, String text) {
        if((text != null && text.length() != 0)) {
            try {
                new WebDriverWait(driver, WAIT_TIME)
                        .until(ExpectedConditions
                                .elementToBeClickable(by))
                        .sendKeys(text);
            } catch (StaleElementReferenceException exception) {
                sleep(100);
                setText(by, text);
            } catch (ElementNotInteractableException exception) {
                sleep(100);
                setText(by, text);
            }
        }
    }

    protected void select(By by, String visibleText) {
        try {
            new Select(new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by)))
                    .selectByVisibleText(visibleText);
        } catch (StaleElementReferenceException exception) {
            sleep(100);
            select(by,visibleText);
        }catch (ElementNotInteractableException exception) {
            sleep(100);
            select(by,visibleText);
        }
    }


    protected String getText(By by) {
        return new WebDriverWait(driver,WAIT_TIME)
                .until(ExpectedConditions
                        .elementToBeClickable(by))
                .getText();
    }
    protected boolean isElementPresent(By by) {
        try {
            new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .presenceOfAllElementsLocatedBy(by));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    protected boolean isElementVisible(By by) {
        try {
            new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .visibilityOfAllElementsLocatedBy(by));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    protected void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void clickJSE(By by){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        WebElement webElement = new WebDriverWait(driver,WAIT_TIME)
                .until(ExpectedConditions
                        .elementToBeClickable(by));
        js.executeScript("arguments[0].click()", webElement);
    }
    protected void scrollInToView(By by){
        try {
            WebElement webElement = new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by));
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).perform();
        } catch (StaleElementReferenceException exception) {
            sleep(100);
            scrollInToView(by);
        }catch (ElementNotInteractableException exception) {
            sleep(100);
            scrollInToView(by);
        }
    }

    protected void clickSelectButton(By by, int ... retries){
        if(retries.length==0){
            retries = new int[1];
            retries[0] = 5;
        }

        if(retries[0]<=0) return;

        try {
            WebElement webElement = new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by));
            click(by);
            String isSelected = webElement.getAttribute("class");
            if(!isSelected.contains("selected-true")){
                sleep(100);
                clickSelectButton(by, retries[0]-1);
            }

        } catch (StaleElementReferenceException exception) {
            sleep(100);
            clickSelectButton(by,retries[0]-1);
        }catch (ElementNotInteractableException exception) {
            sleep(100);
            clickSelectButton(by,retries[0]-1);
        }
    }

    protected void selectOptionButton(String label,String option, int ... retries){
        String selectButton = "//*[text()='XXX']//following::label[text()='YYY'][1]//parent::span"
                .replace("XXX",label)
                .replace("YYY", option);
        By by = By.xpath(selectButton);
        if(retries.length==0){
            retries = new int[1];
            retries[0] = 5;
        }

        if(retries[0]<=0) return;

        try {
            WebElement webElement = new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .elementToBeClickable(by));
            click(by);
            String isSelected = webElement.getAttribute("class");
            if(!isSelected.contains("selected-true")){
                sleep(100);
                clickSelectButton(by, retries[0]-1);
            }

        } catch (StaleElementReferenceException exception) {
            sleep(100);
            clickSelectButton(by,retries[0]-1);
        }catch (ElementNotInteractableException exception) {
            sleep(100);
            clickSelectButton(by,retries[0]-1);
        }
    }
}
