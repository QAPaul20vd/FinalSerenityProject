package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.fasttrack.serenity.pages.HomePage;
import org.fasttrack.serenity.pages.SearchPage;
import org.fasttrack.serenity.pages.ShopPage;
import org.junit.Assert;

import java.util.Collections;
import java.util.List;

public class SearchSteps extends PageObject {

    private HomePage homePage;

    private SearchPage searchPage;

    private ShopPage shopPage;

    public void searchBy(String keyword) {
        homePage.searchBy(keyword);
    }

    public void verifyMatchingKeywordWithSearchHeader(String keyword) {
        Assert.assertEquals("Found items do not contain the keyword!", searchPage.getKeyword(), keyword);
    }

    public void verifyFoundItemsTitleWithKeyword(String keyword) {
        Assert.assertTrue("Some products are not matching the search criteria!", searchPage.verifyEachTitleFoundWithKeyword(keyword));
    }

    public void verifyPageTitleInvalidKeyword() {
        Assert.assertEquals("The search function returned a product!", searchPage.getPageTitle(), "Nothing Found");
    }

    public void verifyAllProductsTitle(String keyword) {
        List<String> listOfTitles = shopPage.getProductsTitles(keyword);
        List<String> listOfFoundTitles = searchPage.getFoundTitles();

        Collections.sort(listOfTitles);
        Collections.sort(listOfFoundTitles);

        Assert.assertEquals("Some products were not found!", listOfTitles, listOfFoundTitles);



    }


}
