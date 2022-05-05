package ru.bart.addressbook.tests;

import org.testng.annotations.Test;
import ru.bart.addressbook.model.GroupData;

public class GroupModifTests extends TestBase{
    @Test
    void groupMod(){
        applicationManager.getNavigationHelper().gotoGroupPage();
        //applicationManager.getGroupHelper().click(By.name("selected[]"));
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getNavigationHelper().gotoGroupEdit();
        applicationManager.getGroupHelper().fillGroupForm(new GroupData("eeee", "aaaaa", "lolo"));

    }
}
