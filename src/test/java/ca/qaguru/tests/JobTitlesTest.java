package ca.qaguru.tests;

import ca.qaguru.lib.TestBase;
import ca.qaguru.pages.LoginPage;
import org.testng.annotations.Test;

public class JobTitlesTest extends TestBase {
    @Test
    public void addJobTitle(){
        new LoginPage(driver)
                .login("admin", "admin123")
                .selectMenu("Admin|Job|Job Titles");
    }
}
