package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;
import java.util.NoSuchElementException;

public class BasePage extends PageObject {

    /**
     * Metoda pentru obtinerea numerelor
     */
    protected int getIntPrice(String price) {
        String arrPrice = price.replaceAll("[^0-9]", "");
        return Integer.valueOf(arrPrice);
    }

//    private int getIntPrice(String price) {
//        String[] arrPrice = price.split(",");
//        String finalPrice = arrPrice[0];
//        return Integer.valueOf(finalPrice);
//    }

    /**
     * Metoda pentru a face click pe un item dintr-o lista
     */
    protected void clickItemFromList(List<WebElementFacade> list, String item) {
        for (WebElementFacade element : list) {
            if (element.getText().equals(item)) {
                clickOn(element);
                break;
            }
        }
    }

    /**
     * Metoda de asteptare ca Preloader-ul sa dispara
     */

    @FindBy(css = "div.preloader")
    private WebElementFacade preloader;

    protected void waitPreloaderDisapear(){
        try {
            preloader.waitUntilNotVisible();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
