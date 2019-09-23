package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.fasttrack.serenity.Utils.Constants;
import org.junit.Assert;

public class CheckoutPage extends BasePage {

    /**
     * Selectors pre-order
     */

    @FindBy(css = "main h1")
    private WebElementFacade mainHeader;

    @FindBy(id = "billing_first_name")
    private WebElementFacade firstNameField;

    @FindBy(id = "billing_last_name")
    private WebElementFacade lastNameField;

    @FindBy(id = "billing_country")
    private WebElementFacade countryDropdown;

    @FindBy(css = "input#billing_address_1")
    private WebElementFacade addressField;

    @FindBy(css = "input#billing_city")
    private WebElementFacade cityField;

    @FindBy(css = "input#billing_postcode")
    private WebElementFacade postcodeField;

    @FindBy(css = "input#billing_phone")
    private WebElementFacade phoneField;

    @FindBy(css = "input#billing_email")
    private WebElementFacade emailField;

    @FindBy(id = "place_order")
    private WebElementFacade placeOrderButton;

    @FindBy(css = ".showcoupon")
    private WebElementFacade showCouponFieldLink;

    @FindBy(id = "coupon_code")
    private WebElementFacade couponInputField;

    @FindBy(css = "button[name*=coupon]")
    private WebElementFacade applyCouponButton;

    @FindBy(css = ".woocommerce-message")
    private WebElementFacade couponMessage;

    @FindBy(css = ".cart-subtotal .amount")
    private WebElementFacade grandSubtotal;

    @FindBy(css = ".cart-discount .amount")
    private WebElementFacade discountDisplayed;

    @FindBy(css = ".order-total .amount")
    private WebElementFacade orderTotal;

    /**
     * Selectors post-order
     */

    @FindBy(css = "p[class*=received]")
    private WebElementFacade successMsg;

    @FindBy(css = ".woocommerce-error")
    private WebElementFacade errorMsg;

    /**
     * Methods pre-order
     */

    public boolean verifyCheckoutPage() {
        waitPreloaderDisappear();
        return mainHeader.getText().equals("Checkout");
    }

    public void typeFirstName(String firstName) {
        typeInto(firstNameField, firstName);
    }

    public void typeLastName(String lastName) {
        typeInto(lastNameField, lastName);
    }

    public void selectCountry(String country) {
        countryDropdown.selectByVisibleText(country);
        Assert.assertEquals(countryDropdown.getSelectedVisibleTextValue(), country);
    }

    public void typeAddress(String address) {
        typeInto(addressField, address);
    }

    public void typeCity(String city) {
        typeInto(cityField, city);
    }

    public void typePostcode(String postcode) {
        typeInto(postcodeField, postcode);
    }

    public void typePhone(String phone) {
        typeInto(phoneField, phone);
    }

    public void typeEmail(String email) {
        if (!emailField.getValue().equals(Constants.USER_EMAIL))
            typeInto(emailField, email);
    }

    public void sendOrder() {
        waitPreloaderDisappear();
        clickOn(placeOrderButton);
    }

    public void clickCouponLink() {
        waitPreloaderDisappear();
        clickOn(showCouponFieldLink);
    }

    public void setCoupon(String coupon) {
        waitPreloaderDisappear();
        typeInto(couponInputField, coupon);
    }

    public void clickApplyCoupon() {
        waitPreloaderDisappear();
        clickOn(applyCouponButton);
    }

    public boolean checkMessageAfterApplyingCoupon() {
        waitPreloaderDisappear();
        return couponMessage.getText().equals("Coupon code applied successfully.");
    }

    public boolean verifyValidCouponCalculation() {
        if (checkMessageAfterApplyingCoupon()) {
            int calculatedDiscount = (getIntValue(grandSubtotal.getText()) * 30) / 100;
            if (calculatedDiscount == getIntValue(discountDisplayed.getText()))
                return getIntValue(orderTotal.getText()) == getIntValue(grandSubtotal.getText()) - getIntValue(discountDisplayed.getText());
        }
        return false;
    }

    /**
     * Methods post-order
     */

    public boolean orderIsSent() {
        waitPreloaderDisappear();
        return successMsg.containsText("Thank you. Your order has been received.");
    }

    public boolean checkErrorMsgIsDisplayed() {
        waitPreloaderDisappear();
        return errorMsg.isCurrentlyVisible();
    }


}
