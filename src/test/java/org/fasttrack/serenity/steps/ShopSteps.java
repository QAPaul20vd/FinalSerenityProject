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
    public void verifyProductPageIsDisplayed() {
        Assert.assertTrue("The product page was not loaded!", productPage.verifyElementIsDisplayed());
    }

    @Step
    public void addProductToCartIfInStock() {

        if (!productPage.checkProductStock())
            productPage.clickAddToCartButton();
    }

    @Step
    public void verifyProductWasAddedToCart() {
        if (!productPage.checkProductStock())
            Assert.assertTrue(productPage.verifyProductWasAddedToCart());
    }

    @Step
    public void inputQuantity(String quantity) {
        if (!productPage.checkProductStock())
            productPage.setQuantity(quantity);
    }

    @Step
    public void verifyCart(){
        Assert.assertTrue(productPage.verifyQuantityAndTotalPriceOfProductsAddedToCart());
    }



}
