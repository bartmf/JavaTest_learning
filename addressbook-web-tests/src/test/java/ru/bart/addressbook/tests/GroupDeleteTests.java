package ru.bart.addressbook.tests;

import org.testng.annotations.Test;
import ru.bart.addressbook.model.GroupData;

public class GroupDeleteTests extends TestBase{
    @Test
    void groupDel (){
        appMan.getNavigationHelper().gotoGroupPage();
        if(!appMan.getGroupHelper().isThereAGroup()){
            appMan.getGroupHelper().createGroup(new GroupData("test", "test", "test"));
            appMan.getNavigationHelper().gotoGroupPage();
        }
        appMan.getGroupHelper().selectGroup();
        appMan.getGroupHelper().deleteGroup();
    }
}
