package ru.bart.rest.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    private String browser;
    private WebDriver wd;
    private RestHelper restHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(Browser.CHROME.browserName())){
                wd = new ChromeDriver();
            } else if (browser.equals(Browser.FIREFOX.browserName())) {
                wd = new FirefoxDriver();
            } else if (browser.equals(Browser.IE.browserName())) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public RestHelper rest() {
        if (restHelper == null) {
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }
}