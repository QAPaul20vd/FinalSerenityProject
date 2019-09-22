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

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTests extends BaseTest {

    @Steps
    private HomeSteps homeSteps;

    @Steps
    private RegisterSteps registerSteps;

    @Steps
    private LoginSteps loginSteps;

    @Test
    public void test1_ValidLogin() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        loginSteps.performLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD);
        loginSteps.verifyLoginOrRegistrationSuccessful(Constants.USER_NAME);
    }

    @Test
    public void test2_InvalidLogin() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        loginSteps.performLogin("", Constants.USER_PASSWORD);
        loginSteps.verifyLoginFailed();
    }

    @Test
    public void test3_LoginLogoutLeftButton() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        loginSteps.performLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD);
        loginSteps.performLeftLogout();
        loginSteps.verifyLogoutSuccessful();
    }

    @Test
    public void test4_LoginLogoutMainButton() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        loginSteps.performLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD);
        loginSteps.performMainLogout();
        loginSteps.verifyLogoutSuccessful();
    }
}
