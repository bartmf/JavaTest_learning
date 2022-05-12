package ru.bart.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.ContactData;
import ru.bart.addressbook.model.GroupData;
import ru.bart.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;


public class AddContactToGroupTEsts extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/avatar.png");
            app.contact().create(new ContactData().withName("test")
                    .withMiddleName("awrf").withLastName("testers").withNickName("sdjfsjd")
                    .withTitle("kdfjnsd,m").withCompany("a;ldiaeo;l").withAddress("asfskl").withHomePhone("2134567890")
                    .withMobilePhone("2345678").withWorkPhone("345678"));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("gro").withHeader("ekrl").withFooter(";lqekr"));
            app.goTo().homePage();
        }

    }

    @Test
    public void testContactAddToGroup() {

        ContactData contact = app.db().contacts().iterator().next();
        Groups before = contact.getGroups();
        Groups groups = app.db().groups();

        if (contact.getGroups().size() == 0) {
            app.contact().changeGroupToAdd(groups.iterator().next(), contact);
            app.goTo().homePage();

        } else if (contact.getGroups().equals(groups)) {
            app.goTo().groupPage();
            int randomNum = (int) (Math.random() * (40 + 1));
            app.group().create(new GroupData().withName("test" + contact.getName())
                    .withHeader("test" + contact.getName()).withFooter("test" + contact.getName()));
            app.goTo().homePage();

            groups = app.db().groups();
            groups.removeAll(contact.getGroups());

        } else {
            groups.removeAll(contact.getGroups());
        }
        GroupData addGroup = groups.iterator().next();
        app.contact().changeGroupToAdd(addGroup, contact);
        Groups after = app.db().contacts().iterator().next().getGroups();
        app.goTo().homePage();

        assertThat(after, equalTo(before.withAdded(addGroup)));
    }
}
