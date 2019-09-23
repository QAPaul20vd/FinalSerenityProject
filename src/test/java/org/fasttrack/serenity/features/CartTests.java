package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.steps.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartTests extends BaseTest {

    @Steps
    private HomeSteps homeSteps;

    @Steps
    private ShopSteps shopSteps;

    @Steps
    private CartSteps cartSteps;

    @Steps
    private ProductSteps productSteps;

    @Steps
    private CheckoutSteps checkoutSteps;

    @Test
    public void test1_VerifySubTotalAndTotalCartForOneProduct() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.verifyCalculationOfSubTotalPrice();
        cartSteps.verifyCartTotal();
    }

    @Test
    public void test2_VerifySubTotalAndTotalCartForOneProductWithModifiedQuantity() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.setQuantityOfProduct("3");
        cartSteps.verifyCalculationOfSubTotalPrice();
        cartSteps.verifyCartTotal();
    }

    @Test
    public void test3_VerifyCartTotalWithMultipleProducts() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.addAllProductsToCart();
        homeSteps.clickCartLink();
        cartSteps.verifyCartTotalMoreItems();
    }

    @Test
    public void test4_RemoveItemsFromCartWithMultipleProducts() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.addAllProductsToCart();
        homeSteps.clickCartLink();
        cartSteps.checkItemsWereRemovedFromCartMoreItems(2);
    }

    @Test
    public void test5_CheckCartTotalAfterRemoveItemsFromCartWithMultipleProducts() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.addAllProductsToCart();
        homeSteps.clickCartLink();
        cartSteps.verifyCartTotalAfterRemovingProducts(3);
    }

    @Test
    public void test6_RemoveProductFromCartOneProductAndVerifyCartIsEmpty() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.removeOneItemFromCart();
        cartSteps.verifyCartIsEmptyAfterRemoveLastProduct();
    }

    @Test
    public void test7_RemoveRestoreOneProductInCart() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.removeOneItemFromCart();
        cartSteps.restoreItemInCartAfterRemoving();
        cartSteps.verifyNameOfProductRestored();
    }

    @Test
    public void test8_ApplyValidCouponToCart(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.setQuantityOfProduct("5");
        cartSteps.applyCouponToOrder("promo30");
        cartSteps.verifyCartTotalWithValidCoupon();
    }

    @Test
    public void test9_ApplyInvalidCouponToCart(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.setQuantityOfProduct("5");
        cartSteps.applyCouponToOrder("promo");
        cartSteps.verifyCartTotalWithInvalidCoupon("promo");
    }

    @Test
    public void test10_ProceedToCheckout() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.proceedToCheckout();
        checkoutSteps.checkIfCheckoutPageIsOpen();
    }
}
