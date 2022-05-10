package ru.bart.addressbook.tests;

import org.testng.annotations.Test;
import ru.bart.addressbook.model.GroupData;

public class GroupModifTests extends TestBase{
    @Test
    void groupMod(){
        appMan.getNavigationHelper().gotoGroupPage();
        if(!appMan.getGroupHelper().isThereAGroup()){
            appMan.getGroupHelper().createGroup(new GroupData("test", "test", "test"));
            appMan.getNavigationHelper().gotoGroupPage();
        }
        appMan.getGroupHelper().selectGroup();
        appMan.getNavigationHelper().gotoGroupEdit();
        appMan.getGroupHelper().fillGroupForm(new GroupData("eeee", "aaaaa", "lolo"));

    }
}
