package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.HomePage;
import org.fasttrack.serenity.pages.MyAccountPage;
import org.fasttrack.serenity.pages.ProductPage;
import org.fasttrack.serenity.pages.ShopPage;
import org.junit.Assert;

public class ShopSteps extends PageObject {

    private HomePage homePage;
    private MyAccountPage myAccountPage;
    private ShopPage shopPage;
    private ProductPage productPage;


    @Step
    public void sortProductsBy(String value) {
        shopPage.selectDropdownValue(value);
    }

    @Step
    public void verifyAscendingSortingPriceByPrice() {
        Assert.assertTrue("Some products are not sorted!", shopPage.verifyAscendingSortingPriceByPrice());
    }

    @Step
    public void verifyDescendingSortingPriceByPrice() {
        Assert.assertTrue("Some products are not sorted!", shopPage.verifyDescendingSortingPriceByPrice());
    }

    @Step
    public void clickProduct(int i) {
        shopPage.selectOneProduct(i);
    }

    @Step
    public void addAllProductsToCart(){
        shopPage.addMoreProductsToCart();
    }





}
