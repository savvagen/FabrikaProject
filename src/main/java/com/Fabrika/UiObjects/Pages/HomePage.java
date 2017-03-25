package com.Fabrika.UiObjects.Pages;


import com.Fabrika.UiObjects.Website;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

import java.util.List;

public class HomePage {

    public static EventFiringWebDriver webDriver;
    public static WebDriverWait wait;
    public static Website website;
    public static RegistrationPage registrationPage;


    public HomePage(EventFiringWebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        website = new Website(driver);
    }

    public static final String HOME_PAGE_URL  = website.BASE_URL + "default/index";
    public static final String HOME_PAGE_TITLE = "Selenium Testing";
    public static final String FLASH_MESSAGE_NOT_LOGIN = "Welcome to Selenium course!" + "\n" + "×";
    public static final String FLASH_MESSAGE_LOGGED_IN = "Welcome to Selenium course, " + registrationPage.USER_FIRST_NAME + "!" + "\n" + "×";

    @FindBy(className = "flash") public static WebElement flashBoard;
    @FindBy(linkText = "Profile") public static WebElement profileButton;
    @FindBy(xpath = ".//*[@id='main']/div[3]/div/div[2]/div/table/tbody") public static WebElement postsTable;


    @Step("Delete post with message: \"{0}\"")
    public void deletePost(String message){
        try {
            List<WebElement> posts = postsTable.findElements(By.tagName("tr"));
            for (WebElement post: posts){
                WebElement postTite = post.findElement(By.tagName("td"));
                if (postTite.getText().contains(message)){
                    String messageId = post.getAttribute("id");
                    System.out.println("Message ID is: " + messageId);
                    //WebElement deletePostButton = webDriver.findElement(By.xpath("//*/a[href='/test_1/default/index/delete/post/" + messageId + "']"));
                    WebElement deleteLink = post.findElement(By.xpath("//*/table/tbody/tr/td/a[@data-w2p_remove='tr']"));
                    deleteLink.click();
                    website.confirmAlert();
                    wait.until(titleIs("Selenium Testing"));
                    website.waitForElement(flashBoard);
                    log.error("Post is deleted successfully!!");
                } else {
                    System.out.println("Post is not found!!");
                    log.error("Post is not found!!");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            log.error("Error occured while post searching: " + "\n" + e.getMessage());
        }
    }


}
