package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;

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

    @FindBy(css = "main .product")
    private List<WebElementFacade> listOfProducts;

    /**
     * Select methods
     */

    public void selectDropdownValue(String value) {
        sortByDropdown.selectByValue(value);
    }

    public void selectOneProduct(int i) {
        waitPreloaderDisappear();
        clickItemFromList(listOfProducts, i);
    }

    /**
     * Ascending verification methods
     */

    private boolean verifyAscendingSortingByPricePerPage(List<WebElementFacade> list) {

        // compare first element in the given list
        for (int i = 0; i < list.size() - 1; i++)
            // with the next element
            for (int j = i + 1; j < list.size(); j++)
                // if the first one is greater than the second one
                if (getIntValue(list.get(i).getText()) > getIntValue(list.get(j).getText()))
                    // than the products are not ascendingly sorted
                    return false;
        return true;
    }

    public boolean verifyAscendingSortingPriceByPriceAllPages() {

        // do the manual verification of ascendingly sorted list
        do {
            // if verification didn't find unordered prices
            if (!verifyAscendingSortingByPricePerPage(listOfAscendingPrices))
                return false;
            // go to next page
            clickOn(nextPageButton);
            // on each page
        } while (nextPageButton.isCurrentlyVisible());

        // verify the sorting of prices from the last page, and if all are sorted the method returns true
        return verifyAscendingSortingByPricePerPage(listOfAscendingPrices);
    }

    /**
     * Descending verification methods
     */

    private boolean verifyDescendingSortingByPricePerPage(List<WebElementFacade> list) {

        for (int i = 0; i < list.size() - 1; i++)
            for (int j = i + 1; j < list.size(); j++)
                if (getIntValue(list.get(i).getText()) < getIntValue(list.get(j).getText()))
                    return false;
        return true;
    }

    public boolean verifyDescendingSortingPriceByPriceAllPages() {
        do {
            if (!verifyDescendingSortingByPricePerPage(listOfDescendingPrices))
                return false;
            clickOn(nextPageButton);
        } while (nextPageButton.isCurrentlyVisible());

        return verifyDescendingSortingByPricePerPage(listOfDescendingPrices);
    }

    /**
     * Adding all products to cart
     */

    public void addMoreProductsToCart() {

        // create a helping list for all products that can be added to cart from the main page
        List<WebElementFacade> toCartList = new ArrayList<>();
        // for each product listed in main page
        for (WebElementFacade item : listOfProducts) {
            //identify those having add to cart button
            if (item.findElement(By.cssSelector("a.button")).getText().equals("Add to cart")) {
                // add the product in the list
                toCartList.add(item);
            }
        }

        // for each product added in helping list
        for (WebElementFacade itemToCart : toCartList) {
            waitPreloaderDisappear();
            // find the add to cart button
            WebElement addButton = itemToCart.findElement(By.cssSelector("a.button"));
            // click the button
            try {
                clickOn(addButton);
            } catch (ElementClickInterceptedException e) {
                waitPreloaderDisappear();
                clickOn(addButton);
            }
        }
    }


    public List<String> getProductsTitles(String keyword) {

        // define a list for all titles in the main page
        List<String> listOfTitles = new ArrayList<>();
        // define a list for all items
        List<WebElement> listOfItems;

        // find on each page the titles equals to the given String
        do {
            //populate the list of items
            waitPreloaderDisappear();
            listOfItems = getDriver().findElements(By.cssSelector("main .product"));

            // for each product in the list of Items
            for (WebElement item : listOfItems) {
                // find the title of the product
                String title = item.findElement(By.cssSelector("h2")).getText();
                // if the title of the product is same with the given String
                if (title.contains(keyword))
                    // add the title to the list of titles
                    listOfTitles.add(title);
            }
            // go to next page
            clickOn(nextPageButton);

        } while (nextPageButton.isCurrentlyVisible());

        // find titles equal to the given String on the last page
        waitPreloaderDisappear();
        listOfItems = getDriver().findElements(By.cssSelector("main .product"));
        for (WebElement item : listOfItems) {
            String title = item.findElement(By.cssSelector("h2")).getText();
            if (title.contains(keyword))
                listOfTitles.add(title);
        }

        // return the list of titles matchind the given String
        return listOfTitles;
    }

}
