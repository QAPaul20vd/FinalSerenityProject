package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ProductPage extends BasePage {

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

    public boolean verifyElementIsDisplayed(){
        waitPreloaderDisapear();
        return skuSpan.isCurrentlyVisible();
    }

    public void clickAddToCartButton(){
        clickOn(addToCartButton);
    }

    public boolean checkProductStock(){
        return outOfStockMessage.isCurrentlyVisible();
    }

    public boolean verifyProductWasAddedToCart(){
        return productAddedToCartMessage.isCurrentlyVisible();
    }
}
