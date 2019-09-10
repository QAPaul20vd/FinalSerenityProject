package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.Utils.Constants;
import org.fasttrack.serenity.steps.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CheckoutTest extends BaseTest {

    @Steps
    private HomeSteps homeSteps;

    @Steps
    private LoginSteps loginSteps;

    @Steps
    private ShopSteps shopSteps;

    @Steps
    private ProductSteps productSteps;

    @Steps
    private CartSteps cartSteps;

    @Steps
    private CheckoutSteps checkoutSteps;

    @Test
    public void loggedInUserValidCheckoutTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        loginSteps.performLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD);
        homeSteps.clickShopLink();
        shopSteps.clickProduct(2);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        checkoutSteps.clickCheckoutLink();
        checkoutSteps.setBillingName("User", "Test");
        checkoutSteps.setBillingAddress("Romania", "Str.Unirii, nr. 8", "Cluj", "400800");
        checkoutSteps.setBillingContact("0564879789", Constants.USER_EMAIL);
        checkoutSteps.placeOrder();
        checkoutSteps.validateOrderIsSent();
    }

    @Test
    public void checkoutLoggedInUserWithoutSomeBillingInformationTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        loginSteps.performLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD);
        homeSteps.clickShopLink();
        shopSteps.clickProduct(2);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        checkoutSteps.clickCheckoutLink();
        checkoutSteps.setBillingName("", "");
        checkoutSteps.placeOrder();
        checkoutSteps.errorMsgIsDisplayed();
    }

    @Test
    public void notLoggedInUserValidCheckoutTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(2);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        checkoutSteps.clickCheckoutLink();
        checkoutSteps.setBillingName("User", "Test");
        checkoutSteps.setBillingAddress("Romania", "Str.Unirii, nr. 8", "Cluj", "400800");
        checkoutSteps.setBillingContact("0564879789", Constants.USER_EMAIL);
        checkoutSteps.placeOrder();
        checkoutSteps.validateOrderIsSent();
    }


}
