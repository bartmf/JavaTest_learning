package ru.bart.addressbook.tests;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.BeforeMethod;
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

public class UserModifTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().
                    withName("TestNotModifName").withLastName("TestNotModifLastName"));
        }
    }

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
    void editUser(UserData user) {
        Users before = app.user().all();
        UserData modUser = before.iterator().next();
        app.user().edit(user.withId(modUser.getId()));
        assertEquals(app.user().count(), before.size());
        Users after = app.user().all();
        assertThat(after, equalToObject(before.without(modUser).withAdded(user)));
    }
}
