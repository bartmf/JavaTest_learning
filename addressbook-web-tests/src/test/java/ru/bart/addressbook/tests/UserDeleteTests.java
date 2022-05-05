package ru.bart.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeleteTests extends TestBase{
    @Test
    void delUser(){
        applicationManager.getNavigationHelper().gotoHomePage();
        applicationManager.getUserHelper().selectUser();
        applicationManager.getUserHelper().delUser();
    }
}
