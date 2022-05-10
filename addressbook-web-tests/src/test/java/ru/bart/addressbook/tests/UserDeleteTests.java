package ru.bart.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserDeleteTests extends TestBase{
    @Test
    void delUser(){
        appMan.getNavigationHelper().gotoHomePage();
        if (!appMan.getUserHelper().isThereUser()) {
            appMan.getUserHelper().creationUser(new UserData("TestModifName", "TestModifLastName"));
        }
        List<UserData> before = appMan.getUserHelper().getUserList();
        appMan.getUserHelper().selectUser();
        appMan.getUserHelper().delUser();
        appMan.getUserHelper().confirm();
        appMan.getNavigationHelper().gotoHomePage();
        List<UserData> after = appMan.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
