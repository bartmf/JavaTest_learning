package ru.bart.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTests extends TestBase{
    @Test
    void groupDel (){
        applicationManager.getNavigationHelper().gotoGroupPage();
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().deleteGroup();
    }
}
