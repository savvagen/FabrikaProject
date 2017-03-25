package com.Fabrika.UiObjects.Pages;


import com.Fabrika.UiObjects.Website;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import static com.Fabrika.UiObjects.Website.*;
import static com.Fabrika.utilites.Waits.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.*;

public class PostPage {

    public static EventFiringWebDriver webDriver;
    public static WebDriverWait wait;
    public static RegistrationPage registrationPage;
    public static HomePage homePage;
    public static LoginPage loginPage;
    public static Website website;

    public PostPage(EventFiringWebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        website = new Website(driver);
    }

    public static final String POST_PAGE_URL = website.BASE_URL + "default/entry_post";
    public static final String POST_PAGE_TITLE = "Selenium Testing";
    public static final String NEW_MESSAGE = "Hello!";

    @FindBy(name = "new_post") public static WebElement postInputField;
    @FindBy(className = "w2p_fw") public static WebElement userIdField;
    @FindBy (xpath = "//*/input[@value='Submit']") public static WebElement submitButton;


    @Step("Load Post Message page")
    public void openPage(){
        website.loginPage().openPage();
        website.loginPage().login(loginPage.USR_EMAIL, loginPage.USER_PASSWORD);
        website.loadPage(POST_PAGE_URL, POST_PAGE_TITLE);
    }

    @Step("Create new post")
    public void createPost(String message){
        website.waitForElement(postInputField);
        website.setElementText(postInputField, message);
        website.clickElement(submitButton);
        website.waitForElement(homePage.postsTable);
        assertVisibility(homePage.flashBoard);
        assertEquals(webDriver.getTitle(), homePage.HOME_PAGE_TITLE);
    }


}
