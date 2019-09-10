package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.steps.CartSteps;
import org.fasttrack.serenity.steps.HomeSteps;
import org.fasttrack.serenity.steps.ProductSteps;
import org.fasttrack.serenity.steps.ShopSteps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CartTests extends BaseTest {

    @Steps
    private HomeSteps homeSteps;

    @Steps
    private ShopSteps shopSteps;

    @Steps
    private CartSteps cartSteps;

    @Steps
    private ProductSteps productSteps;

    @Test
    public void verifySubTotalAndTotalCartForOneProductTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.verifyCalculationOfSubTotalPrice();
        cartSteps.verifyCartTotal();
    }

    @Test
    public void verifySubTotalAndTotalCartForOneProductWithModifiedQuantityTest() {
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
    public void verifyCartTotalWithMultipleProductsTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.addAllProductsToCart();
        homeSteps.clickCartLink();
        cartSteps.verifyCartTotalMoreItems();
    }

    @Test
    public void removeItemsFromCartWithMultipleProductsTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.addAllProductsToCart();
        homeSteps.clickCartLink();
        cartSteps.checkItemsWereRemovedFromCartMoreItems(2);
    }

    @Test
    public void checkCartTotalAfterRemoveItemsFromCartWithMultipleProductsTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.addAllProductsToCart();
        homeSteps.clickCartLink();
        cartSteps.verifyCartTotalAfterRemovingProducts(3);
    }

    @Test
    public void removeProductFromCartOneProductAndVerifyCartIsEmptyTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.removeOneItemFromCart();
        cartSteps.verifyCartIsEmptyAfterRemoveLastProduct();
    }

    @Test
    public void removeRestoreOneProductInCartTest() {
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
    public void applyValidCouponToCartTest(){
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
    public void applyInvalidCouponToCartTest(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.setQuantityOfProduct("5");
        cartSteps.applyCouponToOrder("promo");
        cartSteps.verifyCartTotalWithInvalidCoupon("promo");
    }

}
