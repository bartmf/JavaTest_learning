package ru.bart.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.GroupData;
import ru.bart.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupDeleteTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("test").withHeader("test").withFooter("test"));
            app.goTo().groupPage();
        }
    }
    @Test
    void groupDel (){
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        app.goTo().groupPage();
        Groups after = app.db().groups();
        assertEquals(after.size(), before.size() - 1);
        assertThat(before, equalToObject(after.without(deletedGroup)));
    }
}
