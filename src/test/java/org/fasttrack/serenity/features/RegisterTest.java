package org.fasttrack.serenity.features;


import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.Utils.Constants;
import org.fasttrack.serenity.steps.HomeSteps;
import org.fasttrack.serenity.steps.LoginSteps;
import org.fasttrack.serenity.steps.RegisterSteps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RegisterTest extends BaseTest {

    @Steps
    private HomeSteps homeSteps;

    @Steps
    private RegisterSteps registerSteps;

    @Steps
    private LoginSteps loginSteps;

    @Test
    public void performUserRegistrationTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        registerSteps.performRegister("testareUser2@mailinator.com", "@Qwerty1234poiu");
        loginSteps.verifyLoginOrRegistrationSuccessful("testareUser2");
    }

    @Test
    public void registrationEmptyEmailFieldTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        registerSteps.performRegister("", Constants.USER_PASSWORD);
        registerSteps.verifyEmailMissingErrorMessageIsDisplayed();
    }

    @Test
    public void registrationEmptyPasswordFieldTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        registerSteps.performRegister("testareUser@mailinator.com", "");
        registerSteps.verifyPasswordMissingErrorMessageIsDisplayed();
    }

    @Test
    public void registrationWithExistingEmailTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        registerSteps.performRegister(Constants.USER_EMAIL, "");
        registerSteps.verifyErrorDisplayedIfRegistrationWithAnExistingEmail();
    }


}
