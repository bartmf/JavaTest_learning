package ru.bart.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.bart.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager appMan = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        appMan.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        appMan.stop();
    }

    public ApplicationManager getAppMan() {
        return appMan;
    }
}
