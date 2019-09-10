package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.CheckoutPage;
import org.fasttrack.serenity.pages.HomePage;
import org.junit.Assert;

public class CheckoutSteps extends PageObject {

    private HomePage homePage;

    private CheckoutPage checkoutPage;

    @Step
    public void clickCheckoutLink() {
        homePage.clickMenuLink("Checkout");
    }

    @Step
    public void setBillingName(String firstName, String lastName) {
        checkoutPage.typeFirstName(firstName);
        checkoutPage.typeLastName(lastName);
    }

    @Step
    public void setBillingAddress(String country, String address, String city, String postcode) {
        checkoutPage.selectCountry(country);
        checkoutPage.typeAddress(address);
        checkoutPage.typeCity(city);
        checkoutPage.typePostcode(postcode);
    }

    @Step
    public void setBillingContact(String phone, String email) {
        checkoutPage.typePhone(phone);
        checkoutPage.typeEmail(email);
    }

    @Step
    public void placeOrder() {
        checkoutPage.sendOrder();
    }

    @Step
    public void validateOrderIsSent() {
        Assert.assertTrue(checkoutPage.orderIsSent());
    }

    @Step
    public void errorMsgIsDisplayed() {
        Assert.assertTrue("Error message is not displayed!", checkoutPage.checkErrorMsgIsDisplayed());
    }
}
