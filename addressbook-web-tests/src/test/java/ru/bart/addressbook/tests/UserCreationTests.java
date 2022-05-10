package ru.bart.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreationTests() throws Exception {
    appMan.getNavigationHelper().gotoHomePage();
    List<UserData> before = appMan.getUserHelper().getUserList();
    appMan.getNavigationHelper().godoCreateNewUser();
    UserData user = new UserData("lolo", "popo");
    appMan.getUserHelper().creationUser(user);
    appMan.getNavigationHelper().gotoHomePage();
    List<UserData> after = appMan.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}