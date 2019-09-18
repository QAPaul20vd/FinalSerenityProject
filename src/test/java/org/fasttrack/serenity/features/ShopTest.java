package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.Utils.Constants;
import org.fasttrack.serenity.steps.HomeSteps;
import org.fasttrack.serenity.steps.ProductSteps;
import org.fasttrack.serenity.steps.RegisterSteps;
import org.fasttrack.serenity.steps.ShopSteps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ShopTest extends BaseTest {

    @Steps
    private HomeSteps homeSteps;

    @Steps
    private RegisterSteps registerSteps;

    @Steps
    private ShopSteps shopSteps;

    @Steps
    private ProductSteps productSteps;

    @Test
    public void sortByPriceAscendingTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.sortProductsBy(Constants.SORT_BY_PRICE_ASC);
        shopSteps.verifyAscendingSortingPriceByPrice();
    }

    @Test
    public void sortByPriceDescendingTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.sortProductsBy(Constants.SORT_BY_PRICE_DESC);
        shopSteps.verifyDescendingSortingPriceByPrice();
    }

    @Test
    public void addToCartOneProductTest() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        productSteps.verifyProductPageIsDisplayed();
        productSteps.addProductToCartIfInStock();
        productSteps.verifyProductWasAddedToCart();
    }

    @Test
    public void checkNumberOfItemsAndTotalPriceDisplayedInCartTest(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(1);
        productSteps.inputQuantity("4");
        productSteps.addProductToCartIfInStock();
        productSteps.verifyCart();

    }

}
