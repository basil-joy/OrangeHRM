package ca.qaguru.tests;

import ca.qaguru.lib.TestBase;
import ca.qaguru.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void validLogin(){

        new LoginPage(driver)
                .login("admin","admin123")
                .assertWelcomeMessage();

    }
    @Test
    public void invalidLogin(){
        new LoginPage(driver)
                .invalidLogin("adminasdad","somepassword","Invalid credentials");
    }

}
