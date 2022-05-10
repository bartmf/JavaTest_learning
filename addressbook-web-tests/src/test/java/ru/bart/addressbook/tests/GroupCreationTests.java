package ru.bart.addressbook.tests;

import org.testng.annotations.Test;
import ru.bart.addressbook.model.GroupData;
import ru.bart.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    appMan.goTo().groupPage();
    Groups before = appMan.group().all();
    GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("tester");
    appMan.group().create(group);
    appMan.group().returnToPage();
    Groups after = appMan.group().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalToObject(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
