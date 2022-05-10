package ru.bart.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModifTests extends TestBase{
    @Test
    void groupMod(){
        appMan.getNavigationHelper().gotoGroupPage();
        if(!appMan.getGroupHelper().isThereAGroup()){
            appMan.getGroupHelper().createGroup(new GroupData("test", "test", "test"));
            appMan.getNavigationHelper().gotoGroupPage();
        }
        List<GroupData> before = appMan.getGroupHelper().getGroupList();
        appMan.getGroupHelper().selectGroup(before.size() - 1);
        appMan.getNavigationHelper().gotoGroupEdit();
        GroupData group = new GroupData("eeee", "aaaaa", "lolo");
        appMan.getGroupHelper().fillGroupForm(group);
        appMan.getGroupHelper().updateGroup();
        appMan.getNavigationHelper().gotoGroupPage();
        List<GroupData> after = appMan.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
