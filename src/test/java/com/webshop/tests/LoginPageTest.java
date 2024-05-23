package com.webshop.tests;

import com.webshop.base.BaseTest;
import com.webshop.constants.AppConstants;
import com.webshop.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    private LoginPage loginPage;

    @Parameters({"browser"})
    @BeforeMethod
    protected void setup(String browserName) {
        super.setup(browserName);
        loginPage = homePage.navigateToLoginPage();
    }

    @Test(priority = 1)
    void loginPageTitleTest() {
        Assert.assertEquals(loginPage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE);
    }

    @Test(priority = 2)
    void isRememberMeCheckBoxCheckedTest(){
        Assert.assertFalse(loginPage.isRememberMeCheckBoxChecked());
    }

    @Test(priority = 2)
    void isForgotPasswordLinkExistsTest(){
        Assert.assertTrue(loginPage.isForgotPasswordLinkExists());
    }

    @Test(priority = 2)
    void isLoginButtonEnabledTest(){
        Assert.assertTrue(loginPage.isLoginButtonEnabled());
    }

    @Test(priority = 3)
    void doLoginTest(){
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        loginPage.doLogin(username, password);

        Assert.assertEquals(homePage.getHomePageURL(), prop.getProperty("url"));
        Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE);

        Assert.assertFalse(homePage.isLoginButtonExists());
        Assert.assertTrue(homePage.isLogoutButtonExists());
    }
}
