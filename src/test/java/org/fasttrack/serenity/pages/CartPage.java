package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".cart_item")
    private List<WebElementFacade> listOfProductsInCart;

    @FindBy(css = "tbody .product-remove")
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

    public void clickRemoveItemFromCart(int item){
        clickItemFromList(listOfProductsInCart, item);
    }

    public int getNumbersOfItemsInCart(){
        return listOfProductsInCart.size();
    }


}
