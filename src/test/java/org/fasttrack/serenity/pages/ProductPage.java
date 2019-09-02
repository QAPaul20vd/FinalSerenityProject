package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ProductPage extends BasePage {

    @FindBy(css = ".summary ins span.amount")
    private WebElementFacade actualPrice;

    @FindBy(css = "input[title=Qty]")
    private WebElementFacade inputQtyBox;

    @FindBy(css = ".sku_wrapper")
    private WebElementFacade skuSpan;

    @FindBy(css = "button[type=submit]")
    private WebElementFacade addToCartButton;

    @FindBy(css = ".stock")
    private WebElementFacade outOfStockMessage;

    @FindBy(css = "div[role=alert]")
    private WebElementFacade productAddedToCartMessage;

    @FindBy(css = "div[role=alert] a")
    private WebElementFacade viewCartButton;

    public void clickAddToCartButton() {
        waitPreloaderDisappear();
        clickOn(addToCartButton);
    }

    public void clickViewCartButton(){
        waitPreloaderDisappear();
        clickOn(viewCartButton);
    }

    public int getIntActualPrice() {
        return getIntValue(actualPrice.getText());
    }

    public void setQuantity(String quantity) {
        waitPreloaderDisappear();
        inputQtyBox.clear();
        typeInto(inputQtyBox, quantity);
    }

    /**
     * Verification methods
     */

    public boolean checkProductStock() {
        return outOfStockMessage.isCurrentlyVisible();
    }

    public boolean verifyElementIsDisplayed() {
        waitPreloaderDisappear();
        return skuSpan.isCurrentlyVisible();
    }

    public boolean verifyProductWasAddedToCart() {
        return productAddedToCartMessage.isCurrentlyVisible();
    }

    public boolean verifyQuantityAndTotalPriceOfProductsAddedToCart() {
        return itemsInCart() == getIntValue(inputQtyBox.getValue()) &&
                priceInCart() == getIntActualPrice() * getIntValue(inputQtyBox.getValue());
    }
}
