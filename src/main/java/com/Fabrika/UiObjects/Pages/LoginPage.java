package com.Fabrika.UiObjects.Pages;

import com.Fabrika.UiObjects.Website;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.internal.EventFiringKeyboard;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;
import sun.rmi.runtime.Log;

import static com.Fabrika.UiObjects.Website.*;
import static com.Fabrika.utilites.Waits.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.*;

public class LoginPage {

    public static EventFiringWebDriver webDriver;
    public static WebDriverWait wait;
    public static Website website;

    public LoginPage(EventFiringWebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        website = new Website(driver);
    }

    public static final String LOGIN_PAGE_URL = website.BASE_URL + "default/user/login";
    public static final String LOGIN_PAGE_TITLE  = "Selenium Testing";
    public static final String USR_EMAIL = "savva.genchevskiy@gmail.com";
    public static final String USER_PASSWORD = "19021992qa";

    @FindBy (id = "auth_user_email") public static WebElement emailField;
    @FindBy (id = "auth_user_password") public static WebElement passwordField;
    @FindBy (xpath = "//*/input[@value='Login']") public static WebElement loginButton;


    @Step("Login Page loading")
    public void openPage(){
        website.loadPage(LOGIN_PAGE_URL, LOGIN_PAGE_TITLE);
    }

    @Step("Login user")
    public void login(String email, String password){
        website.waitForElement(emailField);
        website.setElementText(emailField, email);
        website.setElementText(passwordField, password);
        website.clickElement(loginButton);
        website.waitForTitle(homePage.HOME_PAGE_TITLE);
        assertVisibility(website.logOutButton);
    }

}
