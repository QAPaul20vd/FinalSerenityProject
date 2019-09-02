package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.HomePage;

public class HomeSteps extends PageObject {

    private HomePage homePage;

    @Step
    public void navigateToHomePage() {
        homePage.open();
    }

    @Step
    public void clickMyAccountLink() {
        homePage.clickMenuLink("My account");
    }

    @Step
    public void clickCartLink() {
        homePage.clickMenuLink("Cart");
    }

    @Step
    public void clickShopLink() {
        homePage.clickMenuLink("Shop");
    }

    @Step
    public void clickCheckoutLink() {
        homePage.clickMenuLink("Checkout");
    }

}
