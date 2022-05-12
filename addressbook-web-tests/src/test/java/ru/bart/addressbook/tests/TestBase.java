package ru.bart.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.bart.addressbook.appmanager.ApplicationManager;
import ru.bart.addressbook.model.GroupData;
import ru.bart.addressbook.model.Groups;
import ru.bart.addressbook.model.ContactData;
import ru.bart.addressbook.model.Contacts;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void LogTestStart(Method method, Object[] p) {
        logger.info("Start test " + method.getName() + " param: " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method){
        logger.info("Stop test " + method.getName());
    }

    public void verifyGroupListUi() {
        if(Boolean.getBoolean("verifyUI")){
        Groups dbGroups = app.db().groups();
        Groups uiGroups = app.group().all();
        assertThat(uiGroups, equalTo(dbGroups.stream()
                .map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
        }
    }

    public void verifyUserListUi() {
        if(Boolean.getBoolean("verifyUI")){
            Contacts dbContacts = app.db().users();
            Contacts uiContacts = app.user().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((g) -> new ContactData().withId(g.getId())
                            .withName(g.getName())
                            .withAddress(g.getAddress())
                            .withLastName(g.getLastName())).collect(Collectors.toSet())));
        }
    }
}
