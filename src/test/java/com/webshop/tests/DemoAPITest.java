package com.webshop.tests;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoAPITest {
    static Playwright playwright;
    static APIRequest apiRequest;
    static APIRequestContext requestContext;
    static APIResponse response;

    @BeforeTest
    public void setup()
    {
        System.out.println("@BeforeTest");
        playwright = Playwright.create();
        apiRequest = playwright.request();

        String baseURL = "https://pokeapi.co/api/v2/";

        requestContext = apiRequest.newContext(new APIRequest.NewContextOptions().setBaseURL(baseURL));
        response = requestContext.get("pokemon/ditto");
    }

    @AfterTest
    public void tearDown()
    {
        System.out.println("@AfterTest");
        playwright.close();
    }

    @Test
    void printContext()
    {
        String text = response.text();
        System.out.println(text);
        Assert.assertTrue(text.matches(".*abilities.*"));
    }

    @Test
    void printURL()
    {
        String url = response.url();
        System.out.println(url);
        Assert.assertEquals("https://pokeapi.co/api/v2/pokemon/ditto", url);
    }

    @Test
    void printStatus()
    {
        String status = response.statusText();
        System.out.println(status);
        Assert.assertEquals("OK", status);
    }
}
