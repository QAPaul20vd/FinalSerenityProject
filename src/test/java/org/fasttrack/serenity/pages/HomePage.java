package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import java.util.List;

@DefaultUrl("http://qa1.fasttrackit.org:8008/")
public class HomePage extends BasePage {

    /**
     * Main menu selectors
     */

//    @FindBy(css = ".menu > li.menu-item-home")
//    private WebElementFacade homeLink;
//
//    @FindBy(css = ".menu a[href*='my-account']")
//    private WebElementFacade myAccountLink;
//
//    @FindBy(css = "#menu-item-59 a")
//    private WebElementFacade cartLink;
//
//    @FindBy(css = ".menu a[href*='shop']")
//    private WebElementFacade shopLink;
//
//    @FindBy(css = ".menu a[href*='checkout']")
//    private WebElementFacade checkoutLink;

    @FindBy(css = "li[id*=menu]")
    private List<WebElementFacade> listOfMenuLinks;

    @FindBy(css = "div.preloader")
    private WebElementFacade preloader;

    /**
     * Main menu methods
     */

//    public void clickHomeLink() {
//        waitPreloaderDisapear();
//        clickOn(homeLink);
//    }
//
//    public void clickCheckoutLink() {
//        waitPreloaderDisapear();
//        clickOn(checkoutLink);
//    }

    public void clickMenuLink(String item) {
        waitPreloaderDisapear();
        clickItemFromList(listOfMenuLinks, item);
    }


}
