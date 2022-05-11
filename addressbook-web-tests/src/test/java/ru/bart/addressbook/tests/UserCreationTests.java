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
    app.goTo().homePage();
    Users before = app.user().all();
    app.goTo().createNewUser();
    UserData user = new UserData().withName("lolo").withLastName("popo");
    app.user().create(user);
    app.goTo().homePage();
    assertEquals(app.user().count(), before.size() + 1);
    Users after = app.user().all();
    assertThat(after, equalToObject(before.withAdded(user)));
  }
}