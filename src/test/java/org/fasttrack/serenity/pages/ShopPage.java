package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

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
     * Verification methods
     */

    private boolean verifyAscendingSortingByPricePerPage(List<WebElementFacade> list) {

        for (int i = 0; i < list.size() - 1; i++)
            for (int j = i + 1; j < list.size(); j++)
                if (getIntValue(list.get(i).getText()) > getIntValue(list.get(j).getText()))
                    return false;
        return true;
    }

    public boolean verifyAscendingSortingPriceByPriceAllPages() {

        do {
            if (!verifyAscendingSortingByPricePerPage(listOfAscendingPrices))
                return false;
            clickOn(nextPageButton);
        } while (nextPageButton.isCurrentlyVisible());

        return verifyAscendingSortingByPricePerPage(listOfAscendingPrices);
    }

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

    public void addMoreProductsToCart() {

        List<WebElementFacade> toCartList = new ArrayList<>();
        for (WebElementFacade item : listOfProducts) {
            if (item.findElement(By.cssSelector("a.button")).getText().equals("Add to cart")) {
                toCartList.add(item);
            }
        }

        for (WebElementFacade itemToCart : toCartList) {
            waitPreloaderDisappear();
            WebElement addButton = itemToCart.findElement(By.cssSelector("a.button"));
            try {
                clickOn(addButton);
            } catch (ElementClickInterceptedException e) {
                waitPreloaderDisappear();
                clickOn(addButton);
            }
        }
    }


    public List<String> getProductsTitles(String keyword) {

        List<String> listOfTitles = new ArrayList<>();
        List<WebElement> listOfItems;

        do {
            listOfItems = getDriver().findElements(By.cssSelector("main .product"));

            for (WebElement item : listOfItems) {
                String title = item.findElement(By.cssSelector("h2")).getText();
                if (title.contains(keyword))
                    listOfTitles.add(title);
            }
            clickOn(nextPageButton);

        } while (nextPageButton.isCurrentlyVisible());

        listOfItems = getDriver().findElements(By.cssSelector("main .product"));
        for (WebElement item : listOfItems) {
            String title = item.findElement(By.cssSelector("h2")).getText();
            if (title.contains(keyword))
                listOfTitles.add(title);
        }

        return listOfTitles;
    }

}
