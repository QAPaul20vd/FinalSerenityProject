package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.steps.HomeSteps;
import org.fasttrack.serenity.steps.SearchSteps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class SearchTests extends BaseTest {

    @Steps
    private HomeSteps homeSteps;

    @Steps
    private SearchSteps searchSteps;

    private String keyword = "T-Shirt";

    @Test
    public void verifySearchResultsValidKeyword() {
        homeSteps.navigateToHomePage();
        searchSteps.searchBy(keyword);
        searchSteps.verifyMatchingKeywordWithSearchHeader(keyword);
        searchSteps.verifyFoundItemsTitleWithKeyword(keyword);
    }

    @Test
    public void verifySearchResultsInvalidKeyword() {
        homeSteps.navigateToHomePage();
        searchSteps.searchBy(keyword + "1");
        searchSteps.verifyPageTitleInvalidKeyword();
    }

    @Test
    public void verifyProductsTitles(){
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        searchSteps.verifyAllProductsTitle(keyword);
        searchSteps.searchBy(keyword);
    }
}
