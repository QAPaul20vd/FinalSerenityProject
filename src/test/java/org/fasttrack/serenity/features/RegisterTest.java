package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.Utils.Constants;
import org.fasttrack.serenity.steps.LoginSteps;
import org.fasttrack.serenity.steps.RegisterSteps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class RegisterTest {

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Before
    public void maximiseWindow() {
        driver.manage().window().maximize();
    }

    @Steps
    private RegisterSteps registerSteps;

    @Steps
    private LoginSteps loginSteps;

    @Test
    public void performUserRegistrationTest() {
        registerSteps.navigateToHomePage();
        registerSteps.navigateToMyAccountPage();
        registerSteps.performRegister("", Constants.USER_PASSWORD);
        registerSteps.verifyRegistrationFailed();
    }

    @Test
    public void validLoginTest() {
        registerSteps.navigateToHomePage();
        registerSteps.navigateToMyAccountPage();
        loginSteps.performLogin("oricine", "orice");
        loginSteps.verifyLoginFailed();
    }
}
