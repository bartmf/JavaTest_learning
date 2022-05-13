package ru.bart.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressAndMailTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().
                    withName("TestModifName").withLastName("TestModifLastName").withMobilePhone("4567")
                    .withAddress("e7r8uoifojka09m4c 3098234(*(&^%$#").withEmail("apeodj@wsd.ruisl")
                    .withEmail2("email2").withEmail3("email3")
                    .withHomePhone("+9(023)6876490").withWorkPhone("738383").withFax("32456789")
                    .withAddress2("sdoijfsofk").withPhone2("2143567"));
        }
    }
    @Test
    public void userEmailsAddressAndPhoneTest() {
        app.goTo().homePage();
        ContactData user = app.contact().all().stream().iterator().next();
        ContactData userInfoFromEditForm = app.contact().infoFromEditForm(user);

        assertThat(user.getAllEmails(), equalTo(merge(userInfoFromEditForm, "email")));
        assertThat(user.getAddress(), equalTo(merge(userInfoFromEditForm, "address")));
        assertThat(user.getAllPhones(), equalTo(merge(userInfoFromEditForm, "phone")));
    }

    private String merge(ContactData user, String task) {
        if (task.equals("email")) {
            return Arrays.asList(user.getEmail(), user.getEmail2(), user.getEmail3())
                    .stream().filter((s) -> !s.equals(""))
                    .collect(Collectors.joining(" "));
        } else if(task.equals("phone")){
            return Arrays.asList(user.getHomePhone(), user.getWorkPhone(), user.getMobilePhone())
                    .stream().filter((s) -> !s.equals(""))
                    .map(ContactAddressAndMailTests::cleaned)
                    .collect(Collectors.joining("\n"));
        }
        else {
            return Arrays.asList(user.getAddress(), user.getAddress2())
                    .stream().filter((s) -> !s.equals(""))
                    .collect(Collectors.joining("\n"));
        }
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("[-\\s()]", "");
    }
}