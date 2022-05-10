package ru.bart.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    appMan.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = appMan.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test1", "test2", "test3");
    appMan.getGroupHelper().createGroup(group);
    appMan.getGroupHelper().returnToGroupPage();
    List<GroupData> after = appMan.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
