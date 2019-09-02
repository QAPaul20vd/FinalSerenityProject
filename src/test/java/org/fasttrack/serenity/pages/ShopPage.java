package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.ArrayList;
import java.util.List;

public class ShopPage extends BasePage {

    @FindBy(name = "orderby")
    private WebElementFacade sortByDropdown;

    @FindBy(css = ".next")
    private WebElementFacade nextPageButton;

    //    Selecteaza pretul actual(cu reducere, unde e cazul) din produse simple (negrupate),
//    si primul pret dintr-un interval
    @FindBy(css = "main li:not(.product-type-grouped) .price > .amount:first-child, main .price > ins .amount")
    private List<WebElementFacade> listOfAscendingPrices;

    //    Selecteaza pretul actual(cu reducere, unde e cazul) din produse simple (negrupate),
//    si ultimul pret dintr-un interval
    @FindBy(css = "main li:not(.product-type-grouped) .price > .amount:last-child, main .price > ins .amount")
    private List<WebElementFacade> listOfDescendingPrices;

    @FindBy(css = "main .products .product")
    private List<WebElementFacade> listOfProducts;

//    @FindBy(css = "a[class*=add_to_cart_button]")
//    private WebElementFacade addToCart;


    public void selectDropdownValue(String value) {
        sortByDropdown.selectByValue(value);
    }

    public void selectOneProduct(int i) {
        waitPreloaderDisappear();
        clickItemFromList(listOfProducts, i);
    }

    /**
     * Verification methods
     */

    public boolean verifyAscendingSortingPriceByPrice() {

        do {
            List<Boolean> list = new ArrayList<>();
            for (int i = 0; i < listOfAscendingPrices.size() - 1; i++) {
                if (getIntValue(listOfAscendingPrices.get(i).getText()) <= getIntValue(listOfAscendingPrices.get(i + 1).getText())) {
                    list.add(true);
                } else if (getIntValue(listOfAscendingPrices.get(i).getText()) > getIntValue(listOfAscendingPrices.get(i + 1).getText())) {
                    list.add(false);
                }
            }
            for (Boolean item : list) {
                if (!item) {
                    return false;
                }
            }
            clickOn(nextPageButton);
        } while (nextPageButton.isCurrentlyVisible());
        return true;
    }

    public boolean verifyDescendingSortingPriceByPrice() {

        do {
            List<Boolean> list = new ArrayList<>();
            for (int i = 0; i < listOfDescendingPrices.size() - 1; i++) {
                if (getIntValue(listOfDescendingPrices.get(i).getText()) >= getIntValue(listOfDescendingPrices.get(i + 1).getText())) {
                    list.add(true);
                } else if (getIntValue(listOfDescendingPrices.get(i).getText()) < getIntValue(listOfDescendingPrices.get(i + 1).getText())) {
                    list.add(false);
                }
            }
            for (Boolean item : list) {
                if (!item) {
                    return false;
                }
            }
            clickOn(nextPageButton);
        } while (nextPageButton.isCurrentlyVisible());
        return true;
    }

}
