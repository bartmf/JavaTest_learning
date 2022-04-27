package ru.bart.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase{
    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {
        wd.get("https://localhost/addressbook/index.php");
        type(By.name("user"), username);
        type(By.name("pass"), password);
        wd.findElement(By.id("LoginForm")).submit();
    }
}
