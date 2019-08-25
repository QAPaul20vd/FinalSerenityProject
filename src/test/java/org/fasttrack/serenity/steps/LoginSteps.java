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
        Assert.assertTrue("Registration was not done!", myAccountPage.checkEmailErrorMessage());
    }
}
