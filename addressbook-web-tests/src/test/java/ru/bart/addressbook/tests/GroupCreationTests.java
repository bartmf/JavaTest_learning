package ru.bart.addressbook.tests;

import org.testng.annotations.Test;
import ru.bart.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    appMan.getNavigationHelper().gotoGroupPage();
    appMan.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    appMan.getGroupHelper().returnToGroupPage();
  }

}
