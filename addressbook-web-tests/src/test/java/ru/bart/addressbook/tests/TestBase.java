package ru.bart.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.bart.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager appMan = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        appMan.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        appMan.stop();
    }

    public ApplicationManager getAppMan() {
        return appMan;
    }
}
