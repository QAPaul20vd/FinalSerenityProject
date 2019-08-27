package org.fasttrack.serenity.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://qa1.fasttrackit.org:8008/")
public class HomePage extends PageObject {

    /**
     * Main menu selectors
     */

    @FindBy(css = ".menu > li.menu-item-home")
    private WebElementFacade homeLink;

    @FindBy(css = ".menu a[href*='my-account']")
    private WebElementFacade myAccountLink;

    @FindBy(css = "#menu-item-59 a")
    private WebElementFacade cartLink;

    @FindBy(css = ".menu a[href*='shop']")
    private WebElementFacade shopLink;

    @FindBy(css = ".menu a[href*='checkout']")
    private WebElementFacade checkoutLink;

    /**
     * Main menu methods
     */

    public void clickHomeLink() {
        clickOn(homeLink);
    }

    public void clickMyAccountLink() {
        clickOn(myAccountLink);
    }

    public void clickCartLink() {
        clickOn(cartLink);
    }

    public void clickShopLink() {
        clickOn(shopLink);
    }

    public void clickCheckoutLink() {
        clickOn(checkoutLink);
    }

    public void waitTime() {
        waitFor(myAccountLink);
    }


}
