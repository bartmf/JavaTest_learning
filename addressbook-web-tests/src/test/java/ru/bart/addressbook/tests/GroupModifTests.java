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
        appMan.goTo().groupPage();
        if(appMan.group().all().size() == 0){
            appMan.group().create(new GroupData().withName("test").withHeader("test").withFooter("test"));
            appMan.goTo().groupPage();
        }
    }
    @Test
    void groupMod(){
        Groups before = appMan.group().all();
        GroupData modGroupe = before.iterator().next();
        GroupData group = new GroupData().withId(modGroupe.getId()).withName("eeee").
                withHeader("aaaaa").withFooter("lolo");
        appMan.group().modification(group);
        appMan.goTo().groupPage();
        Groups after = appMan.group().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalToObject(before.without(modGroupe).withAdded(group)));
    }
}
