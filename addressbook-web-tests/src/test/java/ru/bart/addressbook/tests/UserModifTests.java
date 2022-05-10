package ru.bart.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;

public class UserModifTests extends TestBase{
    @Test
    void editUser() {
        appMan.getNavigationHelper().gotoHomePage();
        if (!appMan.getUserHelper().isThereUser()) {
            appMan.getUserHelper().creationUser(new UserData("TestName", "TestMiddleName", "TestLastName",
                    "TestNickName", "TestTitle", "TestCompany",
                    "Test Address, Test address 2", "+7686809", "+5467890", "+345678",
                    "+3456789", "test@test.te"));
        }
        appMan.getUserHelper().editUser();
        appMan.getUserHelper().fillInfoNewUser(new UserData("aaaa", "111", "2222", "333",
                "4444", "5555", "666", "7777", "8888", "9999", "000", "qqq"));
        appMan.getUserHelper().click(By.name("update"));
    }
}
