package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.Utils.Constants;
import org.fasttrack.serenity.steps.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
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

//    @Test
//    public void adminLogin(){
//        adminLoginSteps.navigateToAdminPage();
//        adminLoginSteps.performAdminLogin();
//        adminLoginSteps.verifyLoginSuccess();
//        reviewSteps.navigateToCommentsMenu();
//        reviewSteps.verifyCommentsPageIsOpen();
//        reviewSteps.verifyNewCommentIsDisplayed();
//        reviewSteps.approveNewComment();
//        reviewSteps.verifyNewCommentWasApproved();
//        reviewSteps.performAdminLogout();
//    }

    @Test
    public void guestUserRating(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        shopSteps.clickProduct(2);
        reviewSteps.clickReviewTab();
        reviewSteps.setRating(5);
        reviewSteps.insertReviewText("This product is good!");
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
        reviewSteps.verifyCommentIsDisplayed("This product is good!");
    }

    @Test
    public void loggedInUserRating(){
        homeSteps.navigateToHomePage();
        homeSteps.clickMyAccountLink();
        loginSteps.performLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD);
        homeSteps.clickShopLink();
        shopSteps.clickProduct(4);
        reviewSteps.clickReviewTab();
        reviewSteps.setRating(2);
        reviewSteps.insertReviewText("I don't like this product.");
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
        shopSteps.clickProduct(4);
        reviewSteps.clickReviewTab();
        reviewSteps.verifyReviewIsDisplayed(Constants.USER_NAME);
        reviewSteps.verifyCommentIsDisplayed("I don't like this product.");
    }

}
