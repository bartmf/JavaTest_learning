package ru.bart.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;
import ru.bart.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class UserDeleteTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.db().users().size() == 0) {
            app.user().create(new UserData().
                    withName("TestModifName").withLastName("TestModifLastName"));
        }
    }
    @Test
    void delUser(){
        Users before = app.db().users();
        UserData deleteUser = before.iterator().next();
        app.user().delete(deleteUser);
        app.user().confirm();
        app.goTo().homePage();
        assertEquals(app.user().count(), before.size() - 1);
        Users after = app.db().users();
        assertThat(after, equalToObject(before.without(deleteUser)));
        verifyUserListUi();
    }
}
