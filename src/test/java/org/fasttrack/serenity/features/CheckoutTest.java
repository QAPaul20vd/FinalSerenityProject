package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.Utils.Constants;
import org.fasttrack.serenity.steps.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void test1_LoggedInUserValidCheckout() {
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
    public void test2_CheckoutLoggedInUserWithoutSomeBillingInformation() {
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
    public void test3_NotLoggedInUserValidCheckout() {
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

    @Test
    public void test4_AddCouponToOrderAndVerifyPrices(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(2);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.setQuantityOfProduct("5");
        checkoutSteps.clickCheckoutLink();
        checkoutSteps.clickCouponLink();
        checkoutSteps.addCouponToOrder("promo30");
        checkoutSteps.verifyMessageAfterAddingCoupon();
        checkoutSteps.verifyCheckoutTotalWithValidCoupon();


    }


}
