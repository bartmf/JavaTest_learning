package ru.bart.addressbook.tests;

import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;

public class UserDeleteTests extends TestBase{
    @Test
    void delUser(){
        appMan.getNavigationHelper().gotoHomePage();
        if (!appMan.getUserHelper().isThereUser()) {
            appMan.getUserHelper().creationUser(new UserData("TestName", "TestMiddleName", "TestLastName",
                    "TestNickName", "TestTitle", "TestCompany",
                    "Test Address, Test address 2", "+7686809", "+5467890", "+345678",
                    "+3456789", "test@test.te"));
        }
        appMan.getUserHelper().selectUser();
        appMan.getUserHelper().delUser();
    }
}
