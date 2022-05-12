package ru.bart.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressAndMailTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new ContactData().
                    withName("TestModifName").withLastName("TestModifLastName").withMobilePhone("4567")
                    .withAddress("e7r8uoifojka09m4c 3098234(*(&^%$#").withEmail("apeodj@wsd.ruisl")
                    .withEmail2("email2").withEmail3("email3"));
        }
    }
    @Test
    public void userAddressTest(){
        {
            app.goTo().homePage();
            ContactData user = app.user().all().stream().iterator().next();
            ContactData userInfoFromEditForm = app.user().infoFromEditForm(user);

            assertThat(user.getAddress(), equalTo(userInfoFromEditForm.getAddress()));
        }
    }

    @Test
    public void userEmailsTest(){
        app.goTo().homePage();
        ContactData user = app.user().all().stream().iterator().next();
        ContactData userInfoFromEditForm = app.user().infoFromEditForm(user);

        assertThat(user.getAllEmails(), equalTo(mergeEmails(userInfoFromEditForm)));
    }
    private String mergeEmails(ContactData user) {
        return Arrays.asList(user.getHomePhone(), user.getWorkPhone(), user.getMobilePhone())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining(" "));
    }
}
