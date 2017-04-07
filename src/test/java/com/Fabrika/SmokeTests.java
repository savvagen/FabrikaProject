package com.Fabrika;


import com.Fabrika.DataProviders.RegistrationData;
import com.Fabrika.UiObjects.Pages.*;
import com.Fabrika.UiObjects.Verifications;
import com.Fabrika.UiObjects.Website;
import com.Fabrika.utilites.BaseTest;
import com.Fabrika.utilites.Listeners.EventListener;
import com.Fabrika.utilites.Listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;
import java.util.concurrent.TimeUnit;
import static com.Fabrika.utilites.Browser.*;
import static org.testng.Assert.*;


@Title("Smoke tests")
@Description("Tests of main website functionality")
@Listeners(TestListener.class)
public class SmokeTests extends BaseTest {


    @Title("Positive registration")
    @Description("This is a test of registration")
    @Test(priority = 1)
    public void registration() throws Exception{
       regPage.openPage();
       regPage.positiveRegistration(regPage.USER_FIRST_NAME, regPage.USER_LAST_NAME, regPage.USR_EMAIL, regPage.USER_PASSWORD, regPage.USER_PASSWORD, regPage.USER_NICKNAME);
       verifications.verifyRegistration();
    }

    @Title("Negative registration")
    @Description("Description")
    @Test(dataProvider = "regData" , dataProviderClass = RegistrationData.class, priority = 2)
    public void invalidRegistration(String firstName, String lastName, String email, String pass, String rePass, String nickname, String errorMessage) throws Exception {
        regPage.openPage();
        regPage.invalidRegistration(firstName, lastName, email, pass, rePass, nickname);
        regPage.validateError(errorMessage);
    }

    @Title("Positive login")
    @Description("Description")
    @Test(priority = 3)
    public void login() throws Exception{
        loginPage.openPage();
        loginPage.login(loginPage.USR_EMAIL, loginPage.USER_PASSWORD);
        verifications.verifyLogin();
    }

    @Title("Profile deleting")
    @Description("Description")
    @Test(priority = 4)
    public void deleteProfile() throws Exception{
        profilePage.openPage();
        profilePage.delete();
        verifications.verifyProfileDeleting();
    }

    @Title("Post message creation")
    @Description("Description")
    @Test(priority = 5)
    public void postCreation(){
        postPage.openPage();
        postPage.createPost(postPage.NEW_MESSAGE);
        verifications.verifyPost(postPage.NEW_MESSAGE);
    }

    @Title("Post Message deleting")
    @Description("Description")
    @Test(priority = 6)
    public void deletePost() throws Exception {
        loginPage.openPage();
        loginPage.login(loginPage.USR_EMAIL, loginPage.USER_PASSWORD);
        homePage.deletePost(postPage.NEW_MESSAGE);
        verifications.verifyPostIsDeleted(postPage.NEW_MESSAGE);
    }

    @Title("Terms link checking")
    @Description("Description")
    @Test
    public void pressTermsLink(){
        homePage.openPage();
        homePage.pressTerms();
        assertEquals(webDriver.getCurrentUrl(), termsPage.TERMS_PAGE_URL);
        assertEquals(webDriver.getTitle(), termsPage.TERMS_PAGE_TITLE);
    }

    @Title("About link checking")
    @Description("Description")
    @Test
    public void pressAboutLink(){
        homePage.openPage();
        homePage.pressAbout();
        assertEquals(webDriver.getCurrentUrl(), aboutPage.ABOUT_PAGE_URL);
        assertEquals(webDriver.getTitle(), aboutPage.ABOUT_PAGE_TITLE);
    }

    @Title("Description Button checking")
    @Description("Description")
    @Test
    public void pressDescriptionButton(){
        homePage.openPage();
        homePage.pressDescription();
        assertEquals(webDriver.getCurrentUrl(), descriptionPage.DESCRIPTION_PAGE_URL);
        assertEquals(webDriver.getTitle(), descriptionPage.DESCRIPTION_PAGE_TITLE);
    }

    @Title("Contacts link checking")
    @Description("Description")
    @Test
    public void PresContactsLink(){
        homePage.openPage();
        homePage.pressContacts();
        assertEquals(webDriver.getCurrentUrl(), contactPage.CONTACTS_PAGE_URL);
        assertEquals(webDriver.getTitle(), contactPage.CONTACTS_PAGE_TITLE);
    }







}
