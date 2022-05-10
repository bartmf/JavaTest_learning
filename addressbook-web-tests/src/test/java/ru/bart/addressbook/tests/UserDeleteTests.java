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
        appMan.goTo().homePage();
        if (appMan.user().all().size() == 0) {
            appMan.user().create(new UserData().
                    withName("TestModifName").withLastName("TestModifLastName"));
        }
    }
    @Test
    void delUser(){
        Users before = appMan.user().all();
        UserData deleteUser = before.iterator().next();
        appMan.user().delete(deleteUser);
        appMan.user().confirm();
        appMan.goTo().homePage();
        Users after = appMan.user().all();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalToObject(before.without(deleteUser)));
    }
}
