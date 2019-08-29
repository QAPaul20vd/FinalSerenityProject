package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.MyAccountPage;
import org.junit.Assert;

public class LoginSteps extends PageObject {

    private MyAccountPage myAccountPage;

    @Step
    public void performLogin(String email, String password) {
        myAccountPage.setUsernameField(email);
        myAccountPage.setPasswordField(password);
        myAccountPage.clickLoginButton();
    }

    @Step
    public void verifyLoginFailed() {
        Assert.assertTrue("Login was not done!", myAccountPage.loginErrorMsgWhenMissingUsername());
    }

    @Step
    public void verifyLoginOrRegistrationSuccessful(String username) {
        Assert.assertTrue(myAccountPage.checkLoginOrRegistrationSuccess(username));
    }

    @Step
    public void performLeftLogout(){
        myAccountPage.clickLeftLogoutButton();
    }

    @Step
    public void performMainLogout(){
        myAccountPage.clickMainLogoutButton();
    }

    @Step
    public void verifyLogoutSuccessful(){
        Assert.assertTrue(myAccountPage.verifyLogoutDone());
    }
}
