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
public class LoginTests {

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
    public void validLoginTest() {
        registerSteps.navigateToHomePage();
        registerSteps.navigateToMyAccountPage();
        loginSteps.performLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD);
        loginSteps.verifyLoginOrRegistrationSuccessful(Constants.USER_NAME);
    }

    @Test
    public void invalidLoginTest() {
        registerSteps.navigateToHomePage();
        registerSteps.navigateToMyAccountPage();
        loginSteps.performLogin("", Constants.USER_PASSWORD);
        loginSteps.verifyLoginFailed();
    }

    @Test
    public void loginLogoutLeftButtonTest() {
        registerSteps.navigateToHomePage();
        registerSteps.navigateToMyAccountPage();
        loginSteps.performLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD);
        loginSteps.performLeftLogout();
        loginSteps.verifyLogoutSuccessful();
    }

    @Test
    public void loginLogoutMainButtonTest() {
        registerSteps.navigateToHomePage();
        registerSteps.navigateToMyAccountPage();
        loginSteps.performLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD);
        loginSteps.performMainLogout();
        loginSteps.verifyLogoutSuccessful();
    }
}
