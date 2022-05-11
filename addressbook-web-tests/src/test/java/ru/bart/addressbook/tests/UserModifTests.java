package ru.bart.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;
import ru.bart.addressbook.model.Users;

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
    @Test
    void editUser() {
        Users before = app.user().all();
        UserData modUser = before.iterator().next();
        UserData user = new UserData().withId(modUser.getId()).withName("nameModif").withLastName("LastName Modif");
        app.user().edit(user);
        assertEquals(app.user().count(), before.size());
        Users after = app.user().all();
        assertThat(after, equalToObject(before.without(modUser).withAdded(user)));
    }
}
