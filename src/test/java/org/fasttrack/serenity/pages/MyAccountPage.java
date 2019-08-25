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

    public boolean checkRegErrorMessage() {
        return regError.containsText("Error");
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

    public boolean checkEmailErrorMessage() {
        return regError.containsText(("Error").toUpperCase());
    }


}
