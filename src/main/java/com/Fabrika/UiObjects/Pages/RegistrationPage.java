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

import static com.Fabrika.UiObjects.Website.*;
import static com.Fabrika.utilites.Waits.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.*;


public class RegistrationPage {


    public static EventFiringWebDriver webDriver;
    public static WebDriverWait wait;
    public static Website website;


    public RegistrationPage(EventFiringWebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        website = new Website(driver);
    }

    public static final String REGISTRATION_PAGE_URL = website.BASE_URL + "default/user/register";
    public static final String REGISTRATION_PAGE_TITLE  = "Registration";
    public static final String USER_FIRST_NAME = "Savva";
    public static final String USER_LAST_NAME = "Genchevskiy";
    public static final String USR_EMAIL = "savva.genchevskiy+1@gmail.com";
    public static final String USER_PASSWORD = "19021992qa";
    public static final String USER_NICKNAME  = "savva_genchevskiy";

    @FindBy (xpath = "//*/input[@value='Register']") public static WebElement registrationButton;
    @FindBy (id = "auth_user_first_name") public static WebElement firstNameField;
    @FindBy (id = "auth_user_last_name") public static WebElement lastNameField;
    @FindBy (id = "auth_user_email") public static WebElement emailField;
    @FindBy (id = "auth_user_password") public static WebElement passwordField;
    @FindBy(name = "password_two") public static WebElement confirmPassField;
    @FindBy(id = "auth_user_nickname") public static WebElement nickNameField;
    @FindBy(id = "auth_user_image") public static WebElement imageField;
    @FindBy(className = "error") public static WebElement errorMessageField;


    @Step("Registration Page loading")
    public void openPage(){
        website.loadPage(REGISTRATION_PAGE_URL, REGISTRATION_PAGE_TITLE);
    }

    @Step("Registering user")
    public void positiveRegistration(String firstName, String lastName, String email, String password, String rePassword, String nickName) throws Exception{
        register(firstName,lastName, email, password, rePassword, nickName);
        website.waitForTitle( homePage.HOME_PAGE_TITLE);
        assertVisibility(website.logOutButton);
    }

    @Step("Invalid Registration")
    public void invalidRegistration(String firstName, String lastName, String email, String password, String rePassword, String nickName) throws Exception {
        register(firstName,lastName, email, password, rePassword, nickName);
        website.waitForElement(errorMessageField);
        assertVisibility(errorMessageField);
    }

    public void register(String firstName, String lastName, String email, String password, String rePassword, String nickName) throws Exception {
        website.waitForElement(emailField);
        website.setElementText(firstNameField, firstName);
        website.setElementText(lastNameField, lastName);
        website.setElementText(emailField, email);
        website.setElementText(passwordField, password);
        website.setElementText(confirmPassField, rePassword);
        website.setElementText(nickNameField, nickName);
        website.clickElement(registrationButton);
    }

    public void validateError(String errorMessage) throws Exception{
        website.validatePageError(REGISTRATION_PAGE_TITLE, errorMessageField, errorMessage);
    }

}
