package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class MyAccountPage extends PageObject {

    /**
     * Register Selectors
     */

    @FindBy(id = "reg_email")
    private WebElementFacade regEmailField;

    @FindBy(id = "reg_password")
    private WebElementFacade regPasswordField;

    @FindBy(name = "register")
    private WebElementFacade registerButton;

    @FindBy(css = ".woocommerce-error")
    private WebElementFacade regError;

    /**
     * Login Selectors
     */

    @FindBy(id = "username")
    private WebElementFacade usernameField;

    @FindBy(id = "password")
    private WebElementFacade passwordField;

    @FindBy(name = "login")
    private WebElementFacade loginButton;

    @FindBy(id = "rememberme")
    private WebElementFacade rememberMeCheckbox;

    @FindBy(css = "a[href*='lost']")
    private WebElementFacade lostPasswordLink;

    /**
     * Logout Selectors
     */

    @FindBy(css = "nav a[href*='logout']")
    private WebElementFacade leftSideLogoutButton;

    @FindBy(css = "div[class*='MyAccount'] a[href*='logout']")
    private WebElementFacade myAccountMainLogoutButton;

    @FindBy(css = "#meta-2 a[href*=logout]")
    private WebElementFacade rightSideMetaLogoutButton;

    /**
     * My account selectors
     */

    @FindBy(css = "div[class*='MyAccount']>p:first-child")
    private WebElementFacade welcomeMsg;


    /**
     * Register methods
     */

    public void setRegEmailField(String email) {
        typeInto(regEmailField, email);
    }

    public void setRegPasswordField(String password) {
        typeInto(regPasswordField, password);
    }

    public void clickRegisterButton() {
        clickOn(registerButton);
    }


    /**
     * Login methods
     */

    public void setUsernameField(String username) {
        typeInto(usernameField, username);
    }

    public void setPasswordField(String password) {
        typeInto(passwordField, password);
    }

    public void clickLoginButton() {
        clickOn(loginButton);
    }

    public void clickRememberMe() {
        clickOn(rememberMeCheckbox);
    }

    public void clickLostPasswordLink() {
        clickOn(lostPasswordLink);
    }

    /**
     * Logout methods
     */

    public void clickLeftLogoutButton() {
        leftSideLogoutButton.waitUntilVisible();
        clickOn(leftSideLogoutButton);
    }

    public void clickMainLogoutButton() {
        myAccountMainLogoutButton.waitUntilVisible();
        clickOn(myAccountMainLogoutButton);
    }

    public void clickRightLogoutButton() {
        rightSideMetaLogoutButton.waitUntilVisible();
        clickOn(rightSideMetaLogoutButton);
    }

    /**
     * Verification methods
     */

    public boolean errorMessageWhenEmailMissing() {
        return regError.containsText("Please provide a valid email address.");
    }

    public boolean errorMessageWhenPasswordMissing(){
        return regError.containsText("Please enter an account password.");
    }

    public boolean errorMsgWhenTryRegistrationWithAnExistingEmail(){
        return regError.containsText("An account is already registered with your email address. Please log in.");
    }


    public boolean checkLoginOrRegistrationSuccess(String username) {
        return welcomeMsg.containsText("Hello " + username);
    }

    public boolean verifyLogoutDone() {
        return loginButton.isCurrentlyVisible();
    }


}
