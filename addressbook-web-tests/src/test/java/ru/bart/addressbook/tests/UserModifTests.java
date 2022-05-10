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
        appMan.goTo().homePage();
        if (appMan.user().all().size() == 0) {
            appMan.user().create(new UserData().
                    withName("TestNotModifName").withLastName("TestNotModifLastName"));
        }
    }
    @Test
    void editUser() {
        Users before = appMan.user().all();
        UserData modUser = before.iterator().next();
        UserData user = new UserData().withId(modUser.getId()).withName("nameModif").withLastName("LastName Modif");
        appMan.user().edit(user);
        Users after = appMan.user().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalToObject(before.without(modUser).withAdded(user)));
    }
}
