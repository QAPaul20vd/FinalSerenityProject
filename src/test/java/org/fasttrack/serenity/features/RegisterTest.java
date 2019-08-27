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
        registerSteps.performRegister("testareUser1@mailinator.com", "@Qwerty1234poiu");
        loginSteps.verifyLoginOrRegistrationSuccessful("testareUser1");
    }

    @Test
    public void registrationEmptyEmailFieldTest() {
        registerSteps.navigateToHomePage();
        registerSteps.navigateToMyAccountPage();
        registerSteps.performRegister("", Constants.USER_PASSWORD);
        registerSteps.verifyEmailMissingErrorMessageIsDisplayed();
    }

    @Test
    public void registrationEmptyPasswordFieldTest() {
        registerSteps.navigateToHomePage();
        registerSteps.navigateToMyAccountPage();
        registerSteps.performRegister("testareUser@mailinator.com", "");
        registerSteps.verifyPasswordMissingErrorMessageIsDisplayed();
    }

    @Test
    public void registrationWithExistingEmailTest() {
        registerSteps.navigateToHomePage();
        registerSteps.navigateToMyAccountPage();
        registerSteps.performRegister(Constants.USER_EMAIL, "");
        registerSteps.verifyErrorDisplayedIfRegistrationWithAnExistingEmail();
    }


}
