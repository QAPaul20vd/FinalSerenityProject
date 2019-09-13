package org.fasttrack.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
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

    @Step
    public void searchBy(String keyword) {
        homePage.searchBy(keyword);
    }

    @Step
    public void verifyMatchingKeywordWithSearchHeader(String keyword) {
        Assert.assertEquals("Found items do not contain the keyword!", searchPage.getKeyword(), keyword);
    }

    @Step
    public void verifyFoundItemsTitleWithKeyword(String keyword) {
        Assert.assertTrue("Some products are not matching the search criteria!", searchPage.verifyEachTitleFoundWithKeyword(keyword));
    }

    @Step
    public void verifyPageTitleInvalidKeyword() {
        Assert.assertEquals("The search function returned a product!", searchPage.getPageTitle(), "Nothing Found");
    }
/*
    @Step
    public List<String> getListOfSearchedTitlesInShop(String keyword) {
        List<String> listOfTitles = shopPage.getProductsTitles(keyword);
        Collections.sort(listOfTitles);
        System.out.println(listOfTitles);
        return listOfTitles;
    }

    @Step
    public List<String> getListOfFoundTitles() {
        List<String> listOfFoundTitles = searchPage.getFoundTitles();
        Collections.sort(listOfFoundTitles);
        System.out.println(listOfFoundTitles);
        return listOfFoundTitles;
    }*/

    @Step
    public void verifySearchReturnedAllTitlesAvailable(String keyword) {
        List<String> shopList = shopPage.getProductsTitles(keyword);
        Collections.sort(shopList);

        homePage.searchBy(keyword);

        List<String> searchList = searchPage.getFoundTitles();
        Collections.sort(searchList);

        Assert.assertEquals("Some products were not found!", shopList, searchList);
    }


}
