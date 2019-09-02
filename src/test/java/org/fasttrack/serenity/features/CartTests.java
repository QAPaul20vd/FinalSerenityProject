package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.steps.CartSteps;
import org.fasttrack.serenity.steps.HomeSteps;
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

    @Test
    public void viewCartAfterAddingProductTest(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        shopSteps.addProductToCartIfInStock();
        cartSteps.viewCartAfterAddingProduct();
    }

}
