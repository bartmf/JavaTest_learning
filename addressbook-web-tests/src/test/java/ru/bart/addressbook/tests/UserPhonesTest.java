package ru.bart.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;

public class UserPhonesTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().
                    withName("TestModifName").withLastName("TestModifLastName").withMobilePhone("4567")
                    .withHomePhone("+9(023)6876490").withWorkPhone("738383"));
        }
    }

    @Test
    public void testUserPhones(){
        app.goTo().homePage();
        UserData user = app.user().all().stream().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);
    }
}
