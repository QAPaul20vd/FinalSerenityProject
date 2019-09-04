package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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

    public void setQuantity(String qty) {
        waitPreloaderDisappear();
        quantityField.clear();
        quantityField.type(qty);
        clickOn(updateCart);
    }

    public boolean verifyCartTotalOneProduct() {
        waitPreloaderDisappear();
        return getIntValue(eachProductSubtotal.getText()) == getIntValue(cartTotal.getText());
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

//    public void removeItemFromCartMoreItems() {
//
//        getNumbersOfItemsInCart();
//        System.out.println(getNumbersOfItemsInCart());
//
//        for (WebElementFacade item : listOfProductsInCart) {
//
//            try {
//                waitPreloaderDisappear();
//                WebElement itemOut = item.findElement(By.cssSelector("a.remove"));
//                clickOn(itemOut);
//            } catch (StaleElementReferenceException ex) {
//                waitPreloaderDisappear();
//                WebElement itemOut = item.findElement(By.cssSelector("a.remove"));
//                clickOn(itemOut);
//            }
//        }
//
//        System.out.println(getNumbersOfItemsInCart());
//
//
//    }


}
