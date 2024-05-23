package com.webshop.tests;

import com.webshop.base.BaseTest;
import com.webshop.constants.AppConstants;
import com.webshop.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(priority = 1)
    void homePageUrlTest() {
        Assert.assertEquals(homePage.getHomePageURL(), prop.getProperty("url"));
    }

    @Test(priority = 3)
    void homePageTitleTest() {
        Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE);
    }

    // Parameters with DataProviders.
    // See more https://testng.org/parameters.html
    @DataProvider(name = "test1")
    public Object[][] createData1() {
        return new Object[][] {
                { "Computing and Internet", 2 },
                { "Diamond Pave Earrings", 11 },
                { "wrong entry", 0 }
        };
    }

    @Test(dataProvider = "test1",priority = 2)

    void searchTest(String productName, int count) {
        Helper.printLog("Search for: " + productName);
        int resultsCount = homePage.doSearch(productName);
        Assert.assertEquals(resultsCount, count);
    }
}
