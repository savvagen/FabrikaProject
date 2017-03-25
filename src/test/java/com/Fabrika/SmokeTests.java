package com.Fabrika;


import com.Fabrika.DataProviders.RegistrationData;
import com.Fabrika.UiObjects.Pages.*;
import com.Fabrika.UiObjects.Verifications;
import com.Fabrika.UiObjects.Website;
import com.Fabrika.utilites.Listeners.EventListener;
import com.Fabrika.utilites.Listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import static com.Fabrika.utilites.Browser.*;


@Title("Smoke tests")
@Description("Tests of main website functionality")
@Listeners(TestListener.class)
public class SmokeTests {

    public WebDriver driver;
    public WebDriverWait wait;
    public com.Fabrika.utilites.Listeners.EventListener eventListener;
    public EventFiringWebDriver webDriver;
    public static Website website;
    public static RegistrationPage regPage;
    public static Verifications verifications;
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static PostPage postPage;
    public static HomePage homePage;



    @BeforeClass(alwaysRun = true)
    @Parameters({"browserType"})
    public void setUpClass(@Optional String browserType) throws Exception {
        //this.driver = getDriver(getBrowserTypeByProperty());
        this.driver = getBrowser("firefox", false);
        webDriver = new EventFiringWebDriver(driver);
        eventListener = new EventListener();
        webDriver.register(eventListener);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        website = new Website(webDriver);
        regPage = new RegistrationPage(webDriver);
        verifications = new Verifications(webDriver);
        loginPage = new LoginPage(webDriver);
        profilePage = new ProfilePage(webDriver);
        postPage = new PostPage(webDriver);
        homePage = new HomePage(webDriver);
    }


    @AfterClass(alwaysRun = true)
    public void tearDown(){
        if (webDriver != null){
            webDriver.quit();
        }
    }


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
        website.validatePageError(regPage.REGISTRATION_PAGE_TITLE, regPage.errorMessageField, errorMessage);
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







}
