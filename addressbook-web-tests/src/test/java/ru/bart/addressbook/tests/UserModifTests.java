package ru.bart.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

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
        List<UserData> before = appMan.getUserHelper().getUserList();
        appMan.getUserHelper().editUser();
        UserData user = new UserData("nameModif", "LastName Modif");
        appMan.getUserHelper().fillInfoNewUser(user);
        appMan.getUserHelper().click(By.name("update"));
        List<UserData> after = appMan.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        before.add(user);
        Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
