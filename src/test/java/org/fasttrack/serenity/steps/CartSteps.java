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
    public void verifyCartTotalMoreItems() {
        Assert.assertTrue("Cart Total is not correct when multiple items!", cartPage.verifyCartTotalMultipleProducts());
    }

    @Step
    public void checkItemsWereRemovedFromCartMoreItems(int noOfItemsRemoved) {
        Assert.assertTrue("Some items were not removed!", cartPage.checkNoOfItemsInCartAfterRemove(noOfItemsRemoved));
    }

    @Step
    public void removeOneItemFromCart() {
        cartPage.removeItemFromCart();
    }

    @Step
    public void verifyCartIsEmptyAfterRemoveLastProduct() {
        Assert.assertTrue("Cart is not empty!", cartPage.verifyCartIsEmptyAfterRemoveLastProduct());
    }

    @Step
    public void restoreItemInCartAfterRemoving() {
        cartPage.clickUndo();
    }

    @Step
    public void verifyNameOfProductRestored() {
        String productName = cartPage.getCartNameOfProduct();
        Assert.assertTrue("The restored product is not the same!", cartPage.verifyNames(productName));
    }

    @Step
    public void verifyCartTotalAfterRemovingProducts(int itemsRemoved) {
        Assert.assertTrue("Cart total is not correct!", cartPage.verifyCartTotalAfterRemovingSomeProducts(itemsRemoved));
    }

    @Step
    public void applyCouponToOrder(String coupon) {
        cartPage.setCoupon(coupon);
        cartPage.clickApplyCouponButton();
    }

    @Step
    public void verifyCartTotalWithValidCoupon() {
        Assert.assertTrue("Valid coupon calculation is not correct!", cartPage.verifyValidCouponCalculation());
    }

    @Step
    public void verifyCartTotalWithInvalidCoupon(String coupon) {
        Assert.assertTrue("Invalid coupon calculation is not correct!", cartPage.verifyInvalidCouponCalculation(coupon));
    }

    @Step
    public void proceedToCheckout(){
        cartPage.clickCheckoutButton();
    }


}
