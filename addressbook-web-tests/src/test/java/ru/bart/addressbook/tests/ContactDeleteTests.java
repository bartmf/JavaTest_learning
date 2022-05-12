package ru.bart.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.ContactData;
import ru.bart.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactDeleteTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().
                    withName("TestModifName").withLastName("TestModifLastName"));
        }
    }
    @Test
    void delUser(){
        Contacts before = app.db().contacts();
        ContactData deleteUser = before.iterator().next();
        app.contact().delete(deleteUser);
        app.contact().confirm();
        app.goTo().homePage();
        assertEquals(app.contact().count(), before.size() - 1);
        Contacts after = app.db().contacts();
        assertThat(after, equalToObject(before.without(deleteUser)));
        verifyUserListUi();
    }
}
