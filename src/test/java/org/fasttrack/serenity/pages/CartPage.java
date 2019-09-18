package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.nio.file.WatchEvent;
import java.util.List;
import java.util.NoSuchElementException;

public class CartPage extends BasePage {

    @FindBy(css = ".cart_item")
    private List<WebElementFacade> listOfProductsInCart;

    @FindBy(css = "a.remove")
    private WebElementFacade removeButton;

    @FindBy(css = "tbody .product-name")
    private WebElementFacade productName;

    @FindBy(css = "tbody .product-price")
    private WebElementFacade productPrice;

    @FindBy(css = "tbody .qty")
    private WebElementFacade quantityField;

    @FindBy(css = "tbody .product-subtotal")
    private WebElementFacade eachProductSubtotal;

    @FindBy(id = "coupon_code")
    private WebElementFacade couponField;

    @FindBy(name = "apply_coupon")
    private WebElementFacade applyCouponButton;

    @FindBy(css = ".cart-discount .amount")
    private WebElementFacade discountDisplayed;

    @FindBy(name = "update_cart")
    private WebElementFacade updateCart;

    @FindBy(css = ".cart-subtotal span.amount")
    private WebElementFacade grandSubtotal;

    @FindBy(css = ".order-total span.amount")
    private WebElementFacade cartTotal;

    @FindBy(css = "div[role=alert]")
    private WebElementFacade infoMessage;

    @FindBy(css = "ul[role=alert] li")
    private WebElementFacade infoErrorMsg;

    @FindBy(css = ".cart-empty")
    private WebElementFacade cartEmptyMessage;

    @FindBy(css = ".restore-item")
    private WebElementFacade undoButton;

    @FindBy(css = ".cart_totals a")
    private WebElementFacade toCheckoutButton;


    public int getNumbersOfItemsInCart() {
        return listOfProductsInCart.size();
    }

    public boolean verifyCartIsEmptyAfterRemoveLastProduct() {
        waitPreloaderDisappear();
        return cartEmptyMessage.containsText("Your cart is currently empty.");
    }

    public String getCartNameOfProduct() {
        waitPreloaderDisappear();
        return productName.getText().toUpperCase();
    }

    public boolean verifyNames(String prodName) {
        return getCartNameOfProduct().equals(prodName);
    }

    public void clickUndo() {
        waitPreloaderDisappear();
        clickOn(undoButton);
    }

    public void clickCheckoutButton(){
        waitPreloaderDisappear();
        clickOn(toCheckoutButton);
    }
    /**
     * Methods for one item in cart
     */

    public int calculateSubTotalPriceOneProduct() {
        waitPreloaderDisappear();
        return getIntValue(productPrice.getText()) * getIntValue(quantityField.getValue());
    }

    public boolean verifySubTotalPriceOneProduct() {
        waitPreloaderDisappear();
        return calculateSubTotalPriceOneProduct() == getIntValue(eachProductSubtotal.getText());
    }

    public boolean verifyCartTotalOneProduct() {
        waitPreloaderDisappear();
        return getIntValue(eachProductSubtotal.getText()) == getIntValue(cartTotal.getText());
    }

    public void setQuantity(String qty) {
        waitPreloaderDisappear();
        quantityField.clear();
        quantityField.type(qty);
        clickOn(updateCart);
    }

    public void removeItemFromCart() {
        waitPreloaderDisappear();
        clickOn(removeButton);
    }

    /**
     * Methods for multiple items in cart
     */

    public boolean verifyCartTotalMultipleProducts() {

        int sumOfSubTotals = 0;
        for (WebElementFacade item : listOfProductsInCart) {
            int intItemPrice = getIntValue(item.findElement(By.cssSelector(".product-subtotal")).getText());
            sumOfSubTotals += intItemPrice;
        }

        return sumOfSubTotals == getIntValue(cartTotal.getText());
    }

    private void removeItemsFromCart(int noOfItems) {

        for (int i = 0; i < noOfItems; i++) {
            waitPreloaderDisappear();
            WebElementFacade item = listOfProductsInCart.get(i);
            WebElement itemOut = item.findElement(By.cssSelector("a.remove"));
            waitPreloaderDisappear();
            clickOn(itemOut);
        }
    }

    public boolean checkNoOfItemsInCartAfterRemove(int noOfItemsRemoved) {

        waitPreloaderDisappear();
        int initSizeOfCart = getNumbersOfItemsInCart();
        removeItemsFromCart(noOfItemsRemoved);
        waitPreloaderDisappear();
        int finalSizeOfCart = getNumbersOfItemsInCart();

        return finalSizeOfCart == initSizeOfCart - noOfItemsRemoved;
    }

    public boolean verifyCartTotalAfterRemovingSomeProducts(int itemsRemoved) {

        int initCartTotal = getIntValue(cartTotal.getText());

        int sumPricesRemoved = 0;
        for (int i = 0; i < itemsRemoved; i++) {
            waitPreloaderDisappear();
            WebElementFacade item = listOfProductsInCart.get(i);

            WebElement itemSubTotal = item.findElement(By.cssSelector(".product-subtotal"));
            sumPricesRemoved += getIntValue(itemSubTotal.getText());

            WebElement itemOut = item.findElement(By.cssSelector("a.remove"));
            waitPreloaderDisappear();
            clickOn(itemOut);
        }

        waitPreloaderDisappear();
        int finalCartTotal = getIntValue(cartTotal.getText());

        return finalCartTotal == (initCartTotal - sumPricesRemoved);
    }

    /**
     * Methods for coupon
     */

    public void setCoupon(String coupon) {
        waitPreloaderDisappear();
        typeInto(couponField, coupon);
    }

    public void clickApplyCouponButton() {
        waitPreloaderDisappear();
        clickOn(applyCouponButton);
    }

    private boolean checkSuccessfulCouponMessage() {
        waitPreloaderDisappear();
        return infoMessage.containsOnlyText("Coupon code applied successfully.");
    }

    public boolean checkInvalidCouponMessage(String coupon) {
        waitPreloaderDisappear();
        return infoErrorMsg.containsOnlyText("Coupon \"" + coupon + "\" does not exist!");
    }

    /*
    * Coupon name: coupon30
    * Coupon discount: 30% off to hole cart
    * Coupon limits: minimum spent: 50 lei;
    *                it cannot be used in conjunction with other coupons;
    *                unlimited usage;
    * */

    public boolean verifyValidCouponCalculation() {
        if (checkSuccessfulCouponMessage()) {
            int calculatedDiscount = (getIntValue(grandSubtotal.getText()) * 30) / 100;
            if (calculatedDiscount == getIntValue(discountDisplayed.getText()))
                return getIntValue(cartTotal.getText()) == getIntValue(grandSubtotal.getText()) - getIntValue(discountDisplayed.getText());
        }
        return false;
    }

    public boolean verifyInvalidCouponCalculation(String coupon) {
        if (checkInvalidCouponMessage(coupon))
            return getIntValue(grandSubtotal.getText()) == getIntValue(cartTotal.getText());
        return false;
    }

}
