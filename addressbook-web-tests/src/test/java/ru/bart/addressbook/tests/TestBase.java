package ru.bart.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.bart.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager applicationManager = new ApplicationManager("firefox");

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        applicationManager.stop();
    }

    public ApplicationManager getApplicationManager() {
        return applicationManager;
    }
}
