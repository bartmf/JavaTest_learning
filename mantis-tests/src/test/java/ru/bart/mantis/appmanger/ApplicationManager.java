package ru.bart.mantis.appmanger;
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
    private RegistrationHelper registashionHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private ChangePasswordHelper changePasswordHelper;
    private DbHelper dBHelper;

    private SoapHelper soapHelper;


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

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registrations() {
        if (registashionHelper == null) {
            registashionHelper = new RegistrationHelper(this);
        }
        return registashionHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(Browser.CHROME.browserName())) {
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

    public DbHelper db() {
        if (dBHelper == null) {
            dBHelper = new DbHelper(this);
        }
        return dBHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }


    public ChangePasswordHelper changepassword() {
        if (changePasswordHelper == null) {
            changePasswordHelper = new ChangePasswordHelper(this);
        }
        return changePasswordHelper;
    }
    public SoapHelper soap() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}