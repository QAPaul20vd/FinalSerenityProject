package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.fasttrack.serenity.Utils.Constants;

@DefaultUrl("http://qa1.fasttrackit.org:8008/admin")
public class AdminLoginPage extends BasePage {

    /**
     * Login Selectors
     */

    @FindBy(id = "user_login")
    private WebElementFacade usernameInputField;

    @FindBy(id = "user_pass")
    private WebElementFacade passwordInputField;

    @FindBy(id = "wp-submit")
    private WebElementFacade submitButton;

    /**
     * Login Methods
     */

    public void setAdminUsername() {
        waitPreloaderDisappear();
        typeInto(usernameInputField, Constants.ADMIN_USERNAME);
    }

    public void setAdminPassword() {
        typeInto(passwordInputField, Constants.ADMIN_PASSWORD);
    }

    public void clickSubmitButton() {
        clickOn(submitButton);
    }
}
