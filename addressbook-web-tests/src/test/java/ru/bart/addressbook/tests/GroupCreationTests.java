package ru.bart.addressbook.tests;

import org.testng.annotations.Test;
import ru.bart.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{


  @Test
  public void testGroupCreation() throws Exception {
    applicationManager.getNavigationHelper().gotoGroupPage();
    applicationManager.getGroupHelper().initGroupCreation();
    applicationManager.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    applicationManager.getGroupHelper().submitGroupCreation();
    applicationManager.getGroupHelper().returnToGroupPage();
  }

}
