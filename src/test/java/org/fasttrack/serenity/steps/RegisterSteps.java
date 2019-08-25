package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.HomePage;
import org.fasttrack.serenity.pages.MyAccountPage;
import org.junit.Assert;

public class RegisterSteps extends PageObject {

    private HomePage homePage;
    private MyAccountPage myAccountPage;

    @Step
    public void navigateToHomePage(){
        homePage.open();
    }

    @Step
    public void navigateToMyAccountPage(){
        homePage.clickMyAccountLink();
    }

    @Step
    public void performRegister(String email, String password){
        myAccountPage.setEmailField(email);
        myAccountPage.setRegPasswordField(password);
        myAccountPage.clickRegisterButton();
    }

    @Step
    public void verifyRegistrationFailed(){
        Assert.assertTrue("Registration was not done!", myAccountPage.checkRegErrorMessage());
    }

}
