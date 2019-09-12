package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

@DefaultUrl("http://qa1.fasttrackit.org:8008/")
public class HomePage extends BasePage {

    /**
     * Main menu selectors
     */

    @FindBy(css = "li[id*=menu]")
    private List<WebElementFacade> listOfMenuLinks;

    @FindBy(css = "div.preloader")
    private WebElementFacade preloader;

    @FindBy(css = "aside input.search-field")
    private WebElementFacade searchInputField;

    @FindBy(css = "aside label")
    private WebElementFacade searchBox;

    /**
     * Main menu methods
     */

    public void clickMenuLink(String item) {
        waitPreloaderDisappear();
        clickItemFromList(listOfMenuLinks, item);
    }

    public void searchBy(String keyword) {
        typeInto(searchInputField, keyword);
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER); //press enter key
            robot.keyRelease(KeyEvent.VK_ENTER); //release enter key
        } catch (AWTException e){
            e.printStackTrace();
        }
    }


}
