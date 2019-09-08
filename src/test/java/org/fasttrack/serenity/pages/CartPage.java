package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    @FindBy(name = "update_cart")
    private WebElementFacade updateCart;

    @FindBy(css = ".cart-subtotal span.amount")
    private WebElementFacade grandSubtotal;

    @FindBy(css = ".order-total span.amount")
    private WebElementFacade cartTotal;

    @FindBy(css = "div[role=alert]")
    private WebElementFacade infoMessage;

    @FindBy(css = ".cart-empty")
    private WebElementFacade cartEmptyMessage;

    @FindBy(css = ".restore-item")
    private WebElementFacade undoButton;


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

        int initSizeOfCart = getNumbersOfItemsInCart();
        removeItemsFromCart(noOfItemsRemoved);
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


}
