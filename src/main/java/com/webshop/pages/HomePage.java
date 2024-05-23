package com.webshop.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.webshop.utils.Helper;

public class HomePage {
    private Page page;

    private String logoutButton = "a:has-text('Log out')";
    private String loginButton = "a:has-text('Log in')";

    public HomePage(Page page) {
        this.page = page;
    }

    public String getHomePageTitle() {
        String title = page.title();
        Helper.printLog("The page title is : " + title);
        return title;
    }

    public String getHomePageURL() {
        String url = page.url();
        Helper.printLog("The page URL is : " + url);
        return url;
    }

    public int doSearch(String productName) {
        page.locator("#small-searchterms").fill(productName);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).first().click();
        page.waitForLoadState();
        int resultsCount = page.locator("div.page.search-page div.page-body div.search-results div.product-grid div.item-box").count();

        Helper.printLog("The count of found products is : " + resultsCount);
        return resultsCount;
    }

    public LoginPage navigateToLoginPage(){
        page.locator(loginButton).click();
        page.waitForLoadState();

        return new LoginPage(page);
    }

    public boolean isLoginButtonExists(){
        return page.locator(loginButton).isVisible();
    }

    public boolean isLogoutButtonExists(){
        return page.locator(logoutButton).isVisible();
    }
}
