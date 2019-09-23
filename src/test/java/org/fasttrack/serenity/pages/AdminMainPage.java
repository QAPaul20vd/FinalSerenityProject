package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AdminMainPage extends BasePage {

    @FindBy(css = ".ab-top-secondary  > li > a")
    private WebElementFacade welcomeMessage;

    @FindBy(id = "menu-comments")
    private WebElementFacade commentsLink;

    @FindBy(css = ".wrap h1")
    private WebElementFacade commentsPageHeader;

    @FindBy(css = "tr[class*=unapproved]")
    private WebElementFacade newComment;

    public boolean checkAdminLoginSuccessfully() {
        return welcomeMessage.getText().equals("Howdy, admin");
    }

    public void clickCommentsLink() {
        clickOn(commentsLink);
    }

    public boolean checkCommentsPageIsOpen() {
        return commentsPageHeader.getText().equals("Comments");
    }

    public boolean checkNewCommentIsHighlighted() {
        return newComment.isCurrentlyVisible();
    }

    public void checkNewCommentWasApproved() {
        waitPreloaderDisappear();
        newComment.shouldNotBeCurrentlyVisible();
    }

    public void approveNewComment() {
        Actions action = new Actions(getDriver());
        WebElement newCommentRow = getDriver().findElement(By.cssSelector("tr[class*=unapproved]"));
        WebElement approveComment = getDriver().findElement((By.cssSelector(".approve")));
        action.moveToElement(newCommentRow).click(approveComment).build().perform();
    }

    public void clickLogoutAdminButton() {
        Actions action = new Actions(getDriver());
        WebElement wellMsg = getDriver().findElement(By.cssSelector(".ab-top-secondary  > li > a"));
        WebElement logoutButton = getDriver().findElement(By.cssSelector(".quicklinks a[href*=logout]"));
        action.moveToElement(wellMsg).click(logoutButton).build().perform();
    }
}
