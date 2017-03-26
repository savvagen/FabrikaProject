package com.Fabrika.utilites;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Browser {

    public static WebDriver driver;
    public static final String driverPathLinux = "/home/savva/drivers/";


    public static WebDriver getBrowser(String browserType, boolean useGrid) throws Exception {

        if (useGrid){
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName(browserType);
            desiredCapabilities.setPlatform(Platform.LINUX);
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        }

        if (System.getProperty("os.name").toLowerCase().contains("linux")){
            System.out.println("загружены настройки os.name=linux");
            System.setProperty("webdriver.gecko.driver", driverPathLinux + "geckodriver");
            System.setProperty("webdriver.chrome.driver",  driverPathLinux + "chromedriver");
            System.setProperty("webdriver.opera.driver",  driverPathLinux + "operadriver");
        } else if (System.getProperty("os.name").toLowerCase().contains("windows")){
            System.out.println("загружены настройки os.name=windows");
            /*System.setProperty("webdriver.chrome.driver", "bin\\chromedriver.exe")
            System.setProperty("webdriver.gecko.driver", "bin\\geckodriver.exe")
            System.setProperty("webdriver.ie.driver", "bin\\IEDriverServer.exe")
            System.setProperty("webdriver.edge.driver", "C:\\Program Files (x86)\\Microsoft Web Driver\\MicrosoftWebDriver.exe")
            System.setProperty("webdriver.opera.driver", "c:\\XXX\\operadriver.exe")

            System.setProperty("webdriver.opera.path","""C:\\Users\\user\\AppData\\Local\\Programs\\Opera""")
            System.setProperty("webdriver.opera.binary","""C:\\Users\\user\\AppData\\Local\\Programs\\Opera\\launcher.exe""")
            */
        }

        switch (browserType) {
            case "firefox":
                return new FirefoxDriver();
            case "chrome":
                return new ChromeDriver();
            case "opera":
                return new OperaDriver();
            default:
                return driver = new ChromeDriver();
        }
    }

}
