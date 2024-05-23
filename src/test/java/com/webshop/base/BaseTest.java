package com.webshop.base;

import com.microsoft.playwright.Page;
import com.webshop.factory.PlaywrightFactory;
import com.webshop.pages.HomePage;
import com.webshop.utils.Helper;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {
    PlaywrightFactory playwrightFactory;
    Page page;

    protected HomePage homePage;
    protected Properties prop;

    @Parameters({"browser"})
    @BeforeMethod
    protected void setup(String browserName) {
        Helper.printLog("@BeforeTest");
        playwrightFactory = new PlaywrightFactory();
        prop = playwrightFactory.init_prop();

        if (browserName != null)
            prop.setProperty("browser", browserName);

        page = playwrightFactory.initBrowser(prop);
        homePage = new HomePage(page);
    }

    @AfterMethod
    protected void tearDown() {
        Helper.printLog("@AfterTest");
        page.context().browser().close();
    }
}
