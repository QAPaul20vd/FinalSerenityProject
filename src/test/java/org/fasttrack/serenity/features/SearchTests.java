package org.fasttrack.serenity.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.serenity.steps.HomeSteps;
import org.fasttrack.serenity.steps.SearchSteps;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchTests extends BaseTest {

    @Steps
    private HomeSteps homeSteps;

    @Steps
    private SearchSteps searchSteps;

    private String keyword = "T-Shirt";

    @Test
    public void test1_VerifySearchResultsValidKeyword() {
        homeSteps.navigateToHomePage();
        searchSteps.searchBy(keyword);
        searchSteps.verifyMatchingKeywordWithSearchHeader(keyword);
        searchSteps.verifyFoundItemsTitleWithKeyword(keyword);
    }

    @Test
    public void test2_VerifySearchResultsInvalidKeyword() {
        homeSteps.navigateToHomePage();
        searchSteps.searchBy(keyword + "1");
        searchSteps.verifyPageTitleInvalidKeyword();
    }

    @Test
    public void test3_VerifyAllProductsAvailableWereFound() {
        homeSteps.navigateToHomePage();
        homeSteps.clickShopLink();
        searchSteps.verifySearchReturnedAllTitlesAvailable(keyword);
    }


}
