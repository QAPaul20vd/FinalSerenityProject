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
public class ShopTest extends BaseTest {

    @Steps
    private HomeSteps homeSteps;

    @Steps
    private RegisterSteps registerSteps;

    @Steps
    private ShopSteps shopSteps;

    @Steps
    private ProductSteps productSteps;

    @Steps
    private SearchSteps searchSteps;

    @Test
    public void test1_SortByPriceAscending() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.sortProductsBy(Constants.SORT_BY_PRICE_ASC);
        shopSteps.verifyAscendingSortingPriceByPrice();
    }

    @Test
    public void test2_SortByPriceDescending() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.sortProductsBy(Constants.SORT_BY_PRICE_DESC);
        shopSteps.verifyDescendingSortingPriceByPrice();
    }

    @Test
    public void test3_AddToCartOneProduct() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.verifyProductPageIsDisplayed();
        productSteps.addProductToCartIfInStock();
        productSteps.verifyProductWasAddedToCart();
    }

    @Test
    public void test4_CheckNumberOfItemsAndTotalPriceDisplayedInCart() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(2);
        productSteps.inputQuantity("2");
        productSteps.addProductToCartIfInStock();
        productSteps.verifyCart();
    }

    @Test
    public void test5_OutOfStockProductCanNotBeAddedInCart() {
        homeSteps.navigateToHomePage();
        searchSteps.searchBy("Belt");
        searchSteps.gotoFoundItem();
        productSteps.verifyProductIsOutOfStock();
    }
}
