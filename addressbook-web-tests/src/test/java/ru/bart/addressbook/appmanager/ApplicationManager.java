package ru.bart.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public WebDriver wd;
    private UserHelper userHelper;
    private NavigationHelper navigationHelper;
    public GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private String browser;


    String pathToProp = "/home/bart/documents/JavaTest_learning/addressbook-web-tests/src/test/resources/";
    private Properties properties;
    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format(pathToProp + "%s.properties", target))));
        switch (browser) {
            case BrowserType.CHROME:
                wd = new ChromeDriver();
                break;
            case BrowserType.EDGE:
                wd = new EdgeDriver();
                break;
            case BrowserType.IE:
                wd = new InternetExplorerDriver();
                break;
            default: wd = new FirefoxDriver();
        }
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        userHelper = new UserHelper(wd);
        sessionHelper.login(properties.getProperty("web.admin"), properties.getProperty("web.password"));
    }


    public boolean isElementPresent(By by) {
      try {
        userHelper.wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    public void stop() {
        userHelper.wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public UserHelper user() {
        return userHelper;
    }
}
