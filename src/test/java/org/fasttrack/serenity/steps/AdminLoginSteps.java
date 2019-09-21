package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.AdminLoginPage;
import org.fasttrack.serenity.pages.AdminMainPage;
import org.junit.Assert;

public class AdminLoginSteps extends PageObject {

    private AdminLoginPage adminLoginPage;

    private AdminMainPage adminMainPage;

    @Step
    public void navigateToAdminPage() {
        adminLoginPage.open();
    }

    @Step
    public void performAdminLogin() {
        adminLoginPage.setAdminUsername();
        adminLoginPage.setAdminPassword();
        adminLoginPage.clickSubmitButton();
    }

    @Step
    public void verifyLoginSuccess() {
        Assert.assertTrue("Admin login has failed!", adminMainPage.checkAdminLoginSuccessfully());
    }

}
