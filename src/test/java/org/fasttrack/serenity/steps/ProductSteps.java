package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.ProductPage;
import org.junit.Assert;

public class ProductSteps extends PageObject {

    private ProductPage productPage;

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
