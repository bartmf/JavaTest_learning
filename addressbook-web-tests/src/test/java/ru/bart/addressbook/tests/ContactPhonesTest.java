package ru.bart.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhonesTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().
                    withName("TestModifName").withLastName("TestModifLastName").withMobilePhone("4567")
                    .withHomePhone("+9(023)6876490").withWorkPhone("738383"));
        }
    }

    @Test
    public void testUserPhones(){
        app.goTo().homePage();
        ContactData user = app.contact().all().stream().iterator().next();
        ContactData userInfoFromEditForm = app.contact().infoFromEditForm(user);

        assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));
    }

    private String mergePhones(ContactData user) {
        return Arrays.asList(user.getHomePhone(), user.getWorkPhone(), user.getMobilePhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("[-\\s()]", "");
    }
}
