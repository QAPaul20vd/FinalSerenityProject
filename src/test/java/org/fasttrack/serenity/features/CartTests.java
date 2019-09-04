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
    public void verifySubTotalAndTotalCartForOneProductTest(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.verifyCalculationOfSubTotalPrice();
        cartSteps.verifyCartTotal();
    }

    @Test
    public void verifySubTotalAndTotalCartForOneProductWithModifiedQuantityTest(){
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
    public void cartWithMultipleProductsTest(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.addAllProductsToCart();
        homeSteps.clickCartLink();
        cartSteps.verifyCartTotalMoreItems();
    }

//    @Test
//    public void removeItemFromCartWithMultipleProductsTest(){
//        homeSteps.navigateToHomePage();
//        homeSteps.clickShopLink();
//        shopSteps.addAllProductsToCart();
//        homeSteps.clickCartLink();
//        cartSteps.checkItemWasRemovedFromCartMoreItems();
//    }

    @Test
    public void removeProductFromCartOneProductTest(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
        cartSteps.removeOneItemFromCart();
        cartSteps.verifyCartIsEmptyAfterRemoveLastProduct();
    }

}
