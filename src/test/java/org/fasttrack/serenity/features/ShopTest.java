package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.Utils.Constants;

import org.fasttrack.serenity.steps.HomeSteps;
import org.fasttrack.serenity.steps.RegisterSteps;
import org.fasttrack.serenity.steps.ShopSteps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class ShopTest extends BaseTest {


    @Steps
    private HomeSteps homeSteps;

    @Steps
    private RegisterSteps registerSteps;

    @Steps
    private ShopSteps shopSteps;

    @Test
    public void sortByPriceAscendingTest(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.sortProductsBy(Constants.SORT_BY_PRICE_ASC);
        shopSteps.verifyAscendingSortingPriceByPrice();
    }

    @Test
    public void sortByPriceDescendingTest(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.sortProductsBy(Constants.SORT_BY_PRICE_DESC);
        shopSteps.verifyDescendingSortingPriceByPrice();
    }

}