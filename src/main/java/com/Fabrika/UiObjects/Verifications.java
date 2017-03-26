package com.Fabrika.UiObjects;


import com.Fabrika.UiObjects.Pages.HomePage;
import com.Fabrika.UiObjects.Pages.ProfilePage;
import com.Fabrika.UiObjects.Pages.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static com.Fabrika.UiObjects.Website.*;
import static com.Fabrika.utilites.Waits.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.*;



public class Verifications {

    public static EventFiringWebDriver webDriver;
    public static WebDriverWait wait;
    public static Website website;
    public static RegistrationPage registrationPage;
    public static HomePage homePage;
    public static ProfilePage profilePage;

    public Verifications(EventFiringWebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
        website = new Website(driver);
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        profilePage = new ProfilePage(driver);
    }




    @Step("Verify Registration")
    public void verifyRegistration() throws Exception{
        try {
            website.waitForElement(homePage.flashBoard);
            verify(homePage.flashBoard.getText(), homePage.FLASH_MESSAGE_LOGGED_IN);
            verify(webDriver.getTitle(), homePage.HOME_PAGE_TITLE);
            verify(webDriver.getCurrentUrl(), homePage.HOME_PAGE_URL);
            assertVisibility(homePage.profileButton);
            website.makeScreenshot();
        }  finally {
            website.logOut();
            website.makeScreenshot();
        }
    }

    @Step("Verify Login")
    public void verifyLogin() throws Exception{
        try {
            website.waitForElement(homePage.profileButton);
            website.waitForElement(homePage.flashBoard);
            verify(homePage.flashBoard.getText(), homePage.FLASH_MESSAGE_LOGGED_IN);
            verify(webDriver.getTitle(), homePage.HOME_PAGE_TITLE);
            verify(webDriver.getCurrentUrl(), homePage.HOME_PAGE_URL);
            assertVisibility(homePage.profileButton);
            website.makeScreenshot();
        }  finally {
            website.logOut();
            website.makeScreenshot();
        }
    }


    @Step("Verify profile deleting")
    public void verifyProfileDeleting(){
        try {
            website.waitForElement(profilePage.flashMessageField);
            verify(webDriver.getTitle(), homePage.HOME_PAGE_TITLE);
            verify(webDriver.getCurrentUrl(), profilePage.DELETE_URL);
            assertVisibility(profilePage.homeButton);
            verify(profilePage.flashMessageField.getText(), profilePage.DELETE_MESSAGE);
        } finally {
            website.clickElement(profilePage.homeButton);
            website.waitForTitle(homePage.HOME_PAGE_TITLE);
        }
    }

    @Step("verify post with message: \"{0}\"")
    public void verifyPost(String message){
        try {
            List<WebElement> posts = homePage.postsTable.findElements(By.tagName("tr"));
            for (WebElement post: posts){
                WebElement postTite = post.findElement(By.tagName("td"));
                if (postTite.getText().contains(message)){
                    verify(postTite.getText(), message);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            log.error("Error occured while post searching: " + "\n" + e.getMessage());
        } finally {
            website.logOut();
            website.makeScreenshot();
        }
    }

    @Step("verify post with message: \"{0}\" is deleted")
    public void verifyPostIsDeleted(String message){
        try {
            List<WebElement> posts = homePage.postsTable.findElements(By.tagName("tr"));
            for (WebElement post: posts){
                WebElement postTite = post.findElement(By.tagName("td"));
                assertTrue(postTite.getText() != message);
                log.error("Post is deleted!!");
                website.makeScreenshot();
            }
        } catch (Exception e){
            e.printStackTrace();
            log.error("Error occured while post searching: " + "\n" + e.getMessage());
        } finally {
            website.logOut();
            website.makeScreenshot();
        }
    }












}
