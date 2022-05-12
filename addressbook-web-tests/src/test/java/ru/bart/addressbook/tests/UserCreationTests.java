package ru.bart.addressbook.tests;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;
import ru.bart.addressbook.model.Users;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class UserCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validUsers() throws IOException {
    String json = "";
    try(BufferedReader reader = new BufferedReader(new FileReader("/src/test/resources/users.json"))) {
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
    }
    Gson gson = new Gson();
    List<UserData> list = gson.fromJson(json, new TypeToken<List<UserData>>(){}.getType());
    return list.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validUsers")
  public void testUserCreationTests(UserData user){
    app.goTo().homePage();
    Users before = app.db().users();
    app.goTo().createNewUser();
    app.user().create(user);
    app.goTo().homePage();
    assertEquals(app.user().count(), before.size() + 1);
    Users after = app.db().users();
    assertThat(after, equalToObject(before.withAdded(user.withId(after.stream()
            .mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyUserListUi();
  }
}