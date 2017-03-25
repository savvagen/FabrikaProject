package com.Fabrika.utilites;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;



public class DriverFactory {

    public static String driverPath = "/home/savva/drivers/";

    public enum BrowserType{
        FIREFOX("firefox"),
        CHROME("chrome"),
        IE("Internet_Explorer"),
        SAFARI("Safari"),
        OPERA("opera");

        private String value;

        BrowserType(String value){
            this.value = value;
        }

        public String getBrowsername(){
            return this.value;
        }


    }


    public static WebDriver getDriver(BrowserType type) throws Exception{

        if (junitx.util.PropertyManager.getProperty("USE_GRID").equalsIgnoreCase("true")){
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName(type.getBrowsername());
            desiredCapabilities.setPlatform(Platform.LINUX);
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        }


        if (System.getProperty("os.name").toLowerCase().contains("linux")){
            System.out.println("загружены настройки os.name=linux");
            System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver");
            System.setProperty("webdriver.chrome.driver",  driverPath + "chromedriver");
            System.setProperty("webdriver.opera.driver",  driverPath + "operadriver");
        } else if (System.getProperty("os.name").toLowerCase().contains("windows")){
             /*System.setProperty("webdriver.chrome.driver", "bin\\chromedriver.exe")
            System.setProperty("webdriver.gecko.driver", "bin\\geckodriver.exe")
            System.setProperty("webdriver.ie.driver", "bin\\IEDriverServer.exe")
            System.setProperty("webdriver.edge.driver", "C:\\Program Files (x86)\\Microsoft Web Driver\\MicrosoftWebDriver.exe")
            System.setProperty("webdriver.opera.driver", "c:\\XXX\\operadriver.exe")

            System.setProperty("webdriver.opera.path","""C:\\Users\\user\\AppData\\Local\\Programs\\Opera""")
            System.setProperty("webdriver.opera.binary","""C:\\Users\\user\\AppData\\Local\\Programs\\Opera\\launcher.exe""")
            */
        }


        switch (type){
            case CHROME:
                //System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
                return new ChromeDriver();
            case FIREFOX:
                //System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver");
                return new FirefoxDriver();
            case OPERA:
                //System.setProperty("webdriver.opera.driver", driverPath +"operadriver");
                return new OperaDriver();
            //case IE:
            //return new InternetExplorerDriver();
            /*case SAFARI:
                return new SafariDriver();*/
            default:
                return new ChromeDriver();
        }
    }

    public static BrowserType getBrowserTypeByProperty(){
        BrowserType type = null;
        String browsername = junitx.util.PropertyManager.getProperty("BROWSER");
        for (DriverFactory.BrowserType bType: DriverFactory.BrowserType.values()){
            if(bType.getBrowsername().equalsIgnoreCase(browsername)){
                type = bType;
                System.out.println("BROWSER = " + type.getBrowsername());
            }
        }
        return type;
    }


}
