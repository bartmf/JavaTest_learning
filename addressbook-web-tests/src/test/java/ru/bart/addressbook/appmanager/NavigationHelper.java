package ru.bart.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoHomePage(){click(By.linkText("home"));}// Check this func
    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void gotoGroupEdit(){
        click(By.name("edit"));
    }

    public void godoCreateNewUser() {
      wd.findElement(By.linkText("add new")).click();
    }
}
