package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.Utils.Constants;
import org.fasttrack.serenity.steps.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReviewTests extends BaseTest {

    @Steps
    private HomeSteps homeSteps;

    @Steps
    private LoginSteps loginSteps;

    @Steps
    private ShopSteps shopSteps;

    @Steps
    private AdminLoginSteps adminLoginSteps;

    @Steps
    private ReviewSteps reviewSteps;

    @Test
    public void test1_GuestUserRating() {
        String reviewText = "This product is good!";

        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(2);
        reviewSteps.clickReviewTab();
        reviewSteps.setRating(5);
        reviewSteps.insertReviewText(reviewText);
        reviewSteps.typeReviewerData("User", "test@mailinator.com");
        reviewSteps.submitReview();
        adminLoginSteps.navigateToAdminPage();
        adminLoginSteps.performAdminLogin();
        adminLoginSteps.verifyLoginSuccess();
        reviewSteps.navigateToCommentsMenu();
        reviewSteps.verifyCommentsPageIsOpen();
        reviewSteps.verifyNewCommentIsDisplayed();
        reviewSteps.approveNewComment();
        reviewSteps.verifyNewCommentWasApproved();
        reviewSteps.performAdminLogout();
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(2);
        reviewSteps.clickReviewTab();
        reviewSteps.verifyReviewIsDisplayed("User");
        reviewSteps.verifyCommentIsDisplayed(reviewText);
    }

    @Test
    public void test2_LoggedInUserRating() {
        String reviewText = "This product is useless.";

        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        loginSteps.performLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD);
        homeSteps.clickShopLink();
        shopSteps.clickProduct(2);
        reviewSteps.clickReviewTab();
        reviewSteps.setRating(2);
        reviewSteps.insertReviewText(reviewText);
        reviewSteps.submitReview();
        homeSteps.clickMyAccountLink();
        loginSteps.performLeftLogout();
        adminLoginSteps.navigateToAdminPage();
        adminLoginSteps.performAdminLogin();
        adminLoginSteps.verifyLoginSuccess();
        reviewSteps.navigateToCommentsMenu();
        reviewSteps.verifyCommentsPageIsOpen();
        reviewSteps.verifyNewCommentIsDisplayed();
        reviewSteps.approveNewComment();
        reviewSteps.verifyNewCommentWasApproved();
        reviewSteps.performAdminLogout();
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(2);
        reviewSteps.clickReviewTab();
        reviewSteps.verifyReviewIsDisplayed(Constants.USER_NAME);
        reviewSteps.verifyCommentIsDisplayed(reviewText);
    }
}
