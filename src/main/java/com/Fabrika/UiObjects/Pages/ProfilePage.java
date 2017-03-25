package com.Fabrika.UiObjects.Pages;

import com.Fabrika.UiObjects.Website;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.internal.EventFiringKeyboard;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;
import com.jayway.restassured.response.*;

import javax.print.attribute.standard.RequestingUserName;

import static com.jayway.restassured.RestAssured.given;

import java.util.Map;

import static com.Fabrika.UiObjects.Website.*;
import static com.Fabrika.utilites.Waits.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.*;

public class ProfilePage {

    public static EventFiringWebDriver webDriver;
    public static WebDriverWait wait;
    public static Website website;
    public static RegistrationPage registrationPage;
    public static HomePage homePage;
    public static LoginPage loginPage;

    public ProfilePage(EventFiringWebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        website = new Website(driver);
    }

    public static final String PROFILE_PAGE_URL = website.BASE_URL + "default/user/profile";
    public static final String PROFIEL_PAGE_TITLE  = registrationPage.USER_FIRST_NAME + "'s Profile";
    public static final String DELETE_URL = website.BASE_URL + "default/after_delete";
    public static final String DELETE_MESSAGE  = "User DELETED!" + "\n" + "×";

    @FindBy(id = "delete_record") public static WebElement deleteCheckBox;
    @FindBy (id = "auth_user_first_name") public static WebElement firstNameField;
    @FindBy (id = "auth_user_last_name") public static WebElement lastNameField;
    @FindBy (id = "auth_user_email") public static WebElement emailField;
    @FindBy (id = "auth_user_password") public static WebElement passwordField;
    @FindBy(id = "auth_user_nickname") public static WebElement nickNameField;
    @FindBy(id = "auth_user_image") public static WebElement imageField;
    @FindBy (xpath = "//*/input[@value='Submit']") public static WebElement submitButton;
    @FindBy(className = "flash") public static WebElement flashMessageField;
    @FindBy(linkText = "Home") public static WebElement homeButton;

    @Step("Profile page loading")
    public void openPage(){
        website.loginPage().openPage();
        website.loginPage().login(registrationPage.USR_EMAIL, registrationPage.USER_PASSWORD);
        website.loadPage(PROFILE_PAGE_URL, PROFIEL_PAGE_TITLE);
    }

    @Step("Delete profile")
    public void delete(){
        website.clickElement(deleteCheckBox);
        website.confirmAlert();
        website.clickElement(submitButton);
        website.waitForTitle(homePage.HOME_PAGE_TITLE);
    }





}
