package com.webshop.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    private String emailTextBox = "input#Email.email";
    private String passwordTextBox = "input#Password.password";
    private String rememberMeCheckBox = "input#RememberMe";
    private String forgotPasswordLink = "span.forgot-password a:has-text('Forgot password?')";
    private String loginButton = "input:has-text('Log in')";

    public LoginPage(Page page){
        this.page = page;
    }

    public String getLoginPageTitle(){
        return page.title();
    }

    public boolean isRememberMeCheckBoxChecked(){
        return page.locator(rememberMeCheckBox).isChecked();
    }

    public boolean isForgotPasswordLinkExists(){
        return page.locator(forgotPasswordLink).isVisible();
    }

    public boolean isLoginButtonEnabled(){
        return page.locator(loginButton).isEnabled();
    }

    public void doLogin(String username, String password){
        page.fill(emailTextBox, username);
        page.fill(passwordTextBox, password);
        page.check(rememberMeCheckBox);
        page.click(loginButton);

        page.waitForLoadState();
    }
}
