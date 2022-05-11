package ru.bart.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.GroupData;
import ru.bart.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupModifTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test").withHeader("test").withFooter("test"));
            app.goTo().groupPage();
        }
    }
    @Test
    void groupMod(){
        Groups before = app.group().all();
        GroupData modGroupe = before.iterator().next();
        GroupData group = new GroupData().withId(modGroupe.getId()).withName("eeee").
                withHeader("aaaaa").withFooter("lolo");
        app.group().modification(group);
        app.goTo().groupPage();
        assertEquals(app.group().count(), before.size());
        Groups after = app.group().all();
        assertThat(after, equalToObject(before.without(modGroupe).withAdded(group)));
    }
}
