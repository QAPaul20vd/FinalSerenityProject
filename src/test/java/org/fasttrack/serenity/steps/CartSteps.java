package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.CartPage;
import org.fasttrack.serenity.pages.ProductPage;
import org.junit.Assert;

public class CartSteps extends PageObject {

    private CartPage cartPage;
    private ProductPage productPage;

    @Step
    public void viewCartAfterAddingProduct() {
        productPage.clickViewCartButton();
    }

    @Step
    public void verifyCalculationOfSubTotalPrice() {
        Assert.assertTrue("SubTotal is not correct!", cartPage.verifySubTotalPriceOneProduct());
    }

    @Step
    public void setQuantityOfProduct(String qty) {
        cartPage.setQuantity(qty);
    }

    @Step
    public void verifyCartTotal() {
        Assert.assertTrue("Cart Total is not correct!", cartPage.verifyCartTotalOneProduct());
    }

    @Step
    public void verifyCartTotalMoreItems(){
        Assert.assertTrue("Cart Total is not correct when multiple items!",cartPage.verifyCartTotalMultipleProducts());
    }

}
