package ru.bart.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;

public class UserModifTests extends TestBase{
    @Test
    void editUser(){
        applicationManager.getNavigationHelper().gotoHomePage();
        applicationManager.getUserHelper().editUser();
        applicationManager.getUserHelper().fillInfoNewUser(new UserData("aaaa","111","2222","333",
                "4444","5555", "666","7777","8888","9999","000","qqq"));

        applicationManager.getUserHelper().click(By.name("update"));
    }
}
