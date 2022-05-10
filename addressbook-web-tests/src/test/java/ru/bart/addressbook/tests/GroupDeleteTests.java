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
        appMan.goTo().groupPage();
        if(appMan.group().all().size() == 0){
            appMan.group().create(new GroupData().withName("test").withHeader("test").withFooter("test"));
            appMan.goTo().groupPage();
        }
    }
    @Test
    void groupDel (){
        Groups before = appMan.group().all();
        GroupData deletedGroup = before.iterator().next();
        appMan.group().delete(deletedGroup);
        appMan.goTo().groupPage();
        Groups after = appMan.group().all();
        assertEquals(after.size(), before.size() - 1);
        assertThat(before, equalToObject(after.without(deletedGroup)));
    }
}
