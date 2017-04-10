package com.Fabrika.utilites.Listeners;


import com.Fabrika.utilites.Attachments;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;


public class  TestListener extends TestListenerAdapter{

    public static WebDriver driver = null;
    public static EventFiringWebDriver webDriver = null;

    public static final String LOG_FILE_NAME = "log_file";


    private static final Logger log = Logger.getLogger(TestListener.class);


    @Override
    public void onConfigurationSuccess(ITestResult testResult){

    }

    @Override
    public void onConfigurationSkip(ITestResult testResult){

    }

    @Override
    public void onConfigurationFailure(ITestResult testResult){

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {


    }


    @Override
    public void onStart(ITestContext context){

    }



    @Override
    public void onTestStart(ITestResult testResult) {
        log.info("Test Started....");
        log.info("*************** Executing Test " + testResult.getName() + " ********************");
    }

    @Override
    public void onTestSuccess(ITestResult testResult){
        Attachments.attachLog(LOG_FILE_NAME);
        //System.out.println(testResult.getMethod().getMethodName() + " - Passed!");
        log.info("Test '" + testResult.getName() + "' PASSED");
        // This will print the class name in which the method is present
        log.info(testResult.getTestClass());
        // This will print the priority of the method.
        // If the priority is not defined it will print the default priority as - 'o'
        log.info("Priority of this method is " + testResult.getMethod().getPriority());
        log.info("...............................................................");
    }


    @Override
    public void onTestFailure(ITestResult testResult){
        Attachments.attachLog(LOG_FILE_NAME);
        log.error("Test \"" + testResult.getMethod().getMethodName() + "\"" + " - FAILED! ", testResult.getThrowable());
        log.info("Test '" + testResult.getName() + "' FAILED");
        log.info("Priority of this method is " + testResult.getMethod().getPriority());
        log.info("...............................................................");


    }


    @Override
    public void onTestSkipped(ITestResult testResult){
        System.out.println(testResult.getMethod().getMethodName() + " - Skipped!");
        log.info("Test '" + testResult.getName() + "' SKIPPED");
        log.info("..............................................................");
    }


    @Override
    public void onFinish(ITestContext testContext){
        log.info("Tests are finished with: ");
        log.info("Passed tests: " + testContext.getPassedTests().size());
        log.info("Failed tests: " + testContext.getFailedTests().size());
        log.info("Skipped tests: " + testContext.getSkippedTests().size());
    }





}