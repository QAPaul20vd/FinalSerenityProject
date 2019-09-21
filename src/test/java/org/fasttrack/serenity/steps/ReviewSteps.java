package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.AdminMainPage;
import org.fasttrack.serenity.pages.ProductPage;
import org.junit.Assert;

public class ReviewSteps extends PageObject {

    private AdminMainPage adminMainPage;
    private ProductPage productPage;

    /**
     * Admin Steps
     */

    @Step
    public void navigateToCommentsMenu() {
        adminMainPage.clickCommentsLink();
    }

    @Step
    public void verifyCommentsPageIsOpen() {
        Assert.assertTrue("Comments Page was not loaded!", adminMainPage.checkCommentsPageIsOpen());
    }

    @Step
    public void verifyNewCommentIsDisplayed() {
        Assert.assertTrue("New comment is not displayed", adminMainPage.checkNewCommentIsHighlighted());
    }

    @Step
    public void approveNewComment() {
        adminMainPage.approveNewComment();
    }

    @Step
    public void verifyNewCommentWasApproved() {
        adminMainPage.checkNewCommentWasApproved();
    }

    @Step
    public void verifyReviewIsDisplayed(String reviewer) {
        Assert.assertTrue("Last review is not displayed!", productPage.searchForReviewer(reviewer));
    }

    @Step
    public void verifyCommentIsDisplayed(String comment) {
        Assert.assertTrue("Last comment is not displayed!", productPage.searchForComment(comment));
    }


    @Step
    public void performAdminLogout() {
        adminMainPage.clickLogoutAdminButton();
    }

    /**
     * User steps
     */


    @Step
    public void clickReviewTab() {
        productPage.clickReviewTab();
    }

    @Step
    public void setRating(int noStars) {
        productPage.setNumberOfStars(noStars);
    }

    @Step
    public void insertReviewText(String review) {
        productPage.typeReview(review);
    }

    @Step
    public void typeReviewerData(String name, String email) {
        productPage.typeReviewerName(name);
        productPage.typeReviewerEmail(email);
    }

    @Step
    public void submitReview() {
        productPage.clickSubmitButton();
    }
}
