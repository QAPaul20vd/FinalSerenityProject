package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;
import java.util.NoSuchElementException;

public class BasePage extends PageObject {

    /**
     * Extract numbers from String
     */

    protected int getIntValue(String value) {
        waitPreloaderDisappear();

        //Extract from given string all numbers
        String arrPrice = value.replaceAll("[^0-9]", "");

        return Integer.valueOf(arrPrice);
    }

    /**
     * Click one Item in a given List
     */

    protected void clickItemFromList(List<WebElementFacade> list, String item) {

        //Read each String element from the given list
        for (WebElementFacade element : list) {
            if (element.getText().equals(item)) {   //If the element's text is the same as the given String
                waitPreloaderDisappear();
                clickOn(element);       //select the element
                break;            //Stop iteration if element was found
            }
        }
    }

    protected void clickItemFromList(List<WebElementFacade> list, int i) {
        waitPreloaderDisappear();
        clickOn(list.get(i));       //select the element in the given list situated at the given index
    }

    /**
     * Wait Preloader disappear
     */

    @FindBy(css = "div.preloader")
    private WebElementFacade preloader;

    protected void waitPreloaderDisappear() {
        try {
            preloader.waitUntilNotVisible();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Items and Price in Cart
     */

    @FindBy(css = ".menu .cart-contents")
    private WebElementFacade cartContent;      //this reads the cart content from the top right corner

    protected int itemsInCart() {
        waitPreloaderDisappear();
        String strCart = cartContent.getText();
        String[] arrCartContent = strCart.split("-");   //split the String in two arrays
        return getIntValue(arrCartContent[0]);         //return the first array containing the items in cart
    }

    protected int priceInCart() {
        waitPreloaderDisappear();
        String strCart = cartContent.getText();
        String[] arrCartContent = strCart.split("-");   //split the String in two arrays
        return getIntValue(arrCartContent[1]);          //return the second array containing the total price in cart
    }
}
