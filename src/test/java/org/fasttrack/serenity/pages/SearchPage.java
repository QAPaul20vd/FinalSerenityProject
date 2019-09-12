package org.fasttrack.serenity.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(css = ".page-title span")
    private WebElementFacade keyword;

    @FindBy(css = ".page-title")
    private WebElementFacade pageTitle;

    @FindBy(css = "article .entry-header")
    private List<WebElementFacade> foundItemsTitlesList;

    public String getKeyword() {
        waitPreloaderDisappear();
        return keyword.getText();
    }

    public String getPageTitle(){
        waitPreloaderDisappear();
        return pageTitle.getText();
    }

    public boolean verifyEachTitleFoundWithKeyword(String keyword) {

        for (WebElementFacade item : foundItemsTitlesList) {
            if (!item.getText().contains(keyword))
                return false;
        }
        return true;
    }

    public List<String> getFoundTitles(){
        List<String> foundTitleList = new ArrayList<>();
        for(WebElementFacade item : foundItemsTitlesList){
            foundTitleList.add(item.getText());
        }
        return foundTitleList;
    }


}
