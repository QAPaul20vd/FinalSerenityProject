package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.CartPage;
import org.fasttrack.serenity.pages.ProductPage;

public class CartSteps extends PageObject {

    private CartPage cartPage;
    private ProductPage productPage;

    @Step
    public void viewCartAfterAddingProduct(){
        productPage.clickViewCartButton();
    }
}
