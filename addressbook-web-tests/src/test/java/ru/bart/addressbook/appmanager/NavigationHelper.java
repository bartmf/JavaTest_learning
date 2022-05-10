package ru.bart.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void homePage(){click(By.linkText("home"));}
    public void groupPage() {
        click(By.linkText("groups"));
    }

    public void gotoGroupEdit(){

    }

    public void createNewUser() {
      wd.findElement(By.linkText("add new")).click();
    }
}
