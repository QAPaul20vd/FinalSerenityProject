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
        String arrPrice = value.replaceAll("[^0-9]", "");
        return Integer.valueOf(arrPrice);
    }

//    private int getIntValue(String price) {
//        String[] arrPrice = price.split(",");
//        String finalPrice = arrPrice[0];
//        return Integer.valueOf(finalPrice);
//    }

    /**
     * Click one Item in a given List
     */

    protected void clickItemFromList(List<WebElementFacade> list, String item) {
        for (WebElementFacade element : list) {
            if (element.getText().equals(item)) {
                clickOn(element);
                break;
            }
        }
    }

    protected void clickItemFromList(List<WebElementFacade> list, int i) {
        clickOn(list.get(i));
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
    private WebElementFacade cartContent;

    protected int itemsInCart(){
        String strCart = cartContent.getText();
        String[] arrCartContent = strCart.split("-");
        return getIntValue(arrCartContent[0]);
    }

    protected int priceInCart(){
        String strCart = cartContent.getText();
        String[] arrCartContent = strCart.split("-");
        return getIntValue(arrCartContent[1]);
    }
}
