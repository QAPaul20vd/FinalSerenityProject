package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(css = ".product_title")
    private WebElementFacade nameOfProduct;

    @FindBy(css = ".summary ins span.amount")
    private WebElementFacade actualPrice;

    @FindBy(css = "input[title=Qty]")
    private WebElementFacade inputQtyBox;

    @FindBy(css = ".sku_wrapper")
    private WebElementFacade skuSpan;

    @FindBy(css = "button[type=submit]")
    private WebElementFacade addToCartButton;

    @FindBy(css = ".stock")
    private WebElementFacade outOfStockMessage;

    @FindBy(css = "div[role=alert]")
    private WebElementFacade productAddedToCartMessage;

    @FindBy(css = "div[role=alert] a")
    private WebElementFacade viewCartButton;

    /**
     * Review selectors
     */

    @FindBy(css = "a[href=\"#tab-reviews\"]")
    private WebElementFacade reviewsTab;

    @FindBy(css = ".stars span a")
    private List<WebElementFacade> starsList;

    @FindBy(css = "textarea")
    private WebElementFacade yourReviewField;

    @FindBy(id = "author")
    private WebElementFacade reviewersNameField;

    @FindBy(id = "email")
    private WebElementFacade reviewersEmailField;

    @FindBy(id = "submit")
    private WebElementFacade submitReviewButton;

    @FindBy(css = "#comments li strong[class*=author]")
    private List<WebElementFacade> authorsList;

    @FindBy(css = "#comments li .description p")
    private List<WebElementFacade> commentsList;

    /**
     * Product methods
     */

    public void clickAddToCartButton() {
        waitPreloaderDisappear();
        clickOn(addToCartButton);
    }

    public void clickViewCartButton() {
        waitPreloaderDisappear();
        clickOn(viewCartButton);
    }

    public int getIntActualPrice() {
        waitPreloaderDisappear();
        return getIntValue(actualPrice.getText());
    }

    public String getPageNameOfProduct() {
        waitPreloaderDisappear();
        return nameOfProduct.getText().toUpperCase();
    }

    public void setQuantity(String quantity) {
        waitPreloaderDisappear();
        inputQtyBox.clear();
        typeInto(inputQtyBox, quantity);
    }

    /**
     * Review methods
     */

    public void clickReviewTab() {
        waitPreloaderDisappear();
        clickOn(reviewsTab);
    }

    public void setNumberOfStars(int noStars) {
        waitPreloaderDisappear();
        if (noStars >= 1 && noStars <= 5) {
            waitPreloaderDisappear();
            clickItemFromList(starsList, noStars - 1);
        } else {
            System.out.println("Invalid rating");
        }
    }

    public void typeReview(String reviewText) {
        typeInto(yourReviewField, reviewText);
    }

    public void typeReviewerName(String name) {
        typeInto(reviewersNameField, name);
    }

    public void typeReviewerEmail(String email) {
        typeInto(reviewersEmailField, email);
    }

    public void clickSubmitButton() {
        clickOn(submitReviewButton);
    }

    public boolean searchForReviewer(String reviewer) {
        waitPreloaderDisappear();
        return authorsList.get(authorsList.size() - 1).getText().equals(reviewer);
    }

    public boolean searchForComment(String comment) {
        waitPreloaderDisappear();
        return commentsList.get(commentsList.size() - 1).getText().equals(comment);
    }


    /**
     * Verification methods
     */

    public boolean checkProductStock() {
        return outOfStockMessage.isCurrentlyVisible();
    }

    public boolean verifyElementIsDisplayed() {
        waitPreloaderDisappear();
        return skuSpan.isCurrentlyVisible();
    }

    public boolean verifyProductWasAddedToCart() {
        waitPreloaderDisappear();
        return productAddedToCartMessage.isCurrentlyVisible();
    }

    public boolean verifyQuantityAndTotalPriceOfProductsAddedToCart() {
        waitPreloaderDisappear();
        return itemsInCart() == getIntValue(inputQtyBox.getValue()) &&
                priceInCart() == getIntActualPrice() * getIntValue(inputQtyBox.getValue());
    }
}
