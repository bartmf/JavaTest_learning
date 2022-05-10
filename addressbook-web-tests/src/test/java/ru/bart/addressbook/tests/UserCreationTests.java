package ru.bart.addressbook.tests;

import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;
import ru.bart.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreationTests() throws Exception {
    appMan.goTo().homePage();
    Users before = appMan.user().all();
    appMan.goTo().createNewUser();
    UserData user = new UserData().withName("lolo").withLastName("popo");
    appMan.user().create(user);
    appMan.goTo().homePage();
    Users after = appMan.user().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalToObject(before.withAdded(user)));
  }
}