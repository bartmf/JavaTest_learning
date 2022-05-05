package ru.bart.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoHomePage(){click(By.linkText("home"));}
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
