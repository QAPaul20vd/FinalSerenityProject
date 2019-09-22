package org.fasttrack.serenity.features;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.Utils.Constants;
import org.fasttrack.serenity.steps.HomeSteps;
import org.fasttrack.serenity.steps.LoginSteps;
import org.fasttrack.serenity.steps.RegisterSteps;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Date;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterTest extends BaseTest {

    @Steps
    private HomeSteps homeSteps;

    @Steps
    private RegisterSteps registerSteps;

    @Steps
    private LoginSteps loginSteps;

    @Test
    public void test1_PerformUserRegistration() {

        Date date = new Date();
        long timeStamp = date.getTime();

        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        registerSteps.performRegister("testareUser" + timeStamp + "@mailinator.com", "@Qwerty1234poiu");
        loginSteps.verifyLoginOrRegistrationSuccessful("testareUser" + timeStamp);
    }

    @Test
    public void test2_RegistrationEmptyEmailField() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        registerSteps.performRegister("", Constants.USER_PASSWORD);
        registerSteps.verifyEmailMissingErrorMessageIsDisplayed();
    }

    @Test
    public void test3_RegistrationEmptyPasswordField() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        registerSteps.performRegister("testareUser@mailinator.com", "");
        registerSteps.verifyPasswordMissingErrorMessageIsDisplayed();
    }

    @Test
    public void test4_RegistrationWithExistingEmail() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        registerSteps.performRegister(Constants.USER_EMAIL, "");
        registerSteps.verifyErrorDisplayedIfRegistrationWithAnExistingEmail();
    }


}
