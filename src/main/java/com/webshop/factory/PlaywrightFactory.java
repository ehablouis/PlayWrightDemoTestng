package com.webshop.factory;

import com.microsoft.playwright.*;
import com.webshop.utils.Helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;

    Page page;
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();

    Properties prop;

    public Page initBrowser(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        String url = prop.getProperty("url").trim();
        boolean headless = Boolean.parseBoolean(prop.getProperty("headless").trim());

        Helper.printLog("Browser name is: " + browserName);

        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            default:
                System.out.println("Please enter the right browser name ....");
                break;
        }

        browserContext = browser.newContext();
        page = browserContext.newPage();

        page.navigate(url);
        page.waitForLoadState();

        tlPage.set(page);
        return page;
    }

    /*
    * Initialize the properties from config file (src/test/resources/config/config.properties).
     */
    public Properties init_prop() {
        try {
            FileInputStream configFile = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return  prop;
    }

    public static Page getPage(){
        return tlPage.get();
    }

    public static String tackScreenshot(){
        String path =  ".\\outputs\\screenshot\\" + System.currentTimeMillis() + ".png";
        getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        return "..\\" + path;
    }
}
