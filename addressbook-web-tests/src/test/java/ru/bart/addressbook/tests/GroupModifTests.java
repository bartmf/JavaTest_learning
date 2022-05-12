package ru.bart.addressbook.tests;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.GroupData;
import ru.bart.addressbook.model.Groups;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupModifTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        String json = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("/src/test/resources/groups.json"))) {
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();

            }
        }
        Gson gson = new Gson();
        List<GroupData> list = gson.fromJson(json, new TypeToken<List<GroupData>>() {
        }.getType());
        return list.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test").withHeader("test").withFooter("test"));
            app.goTo().groupPage();
        }
    }

    @Test(dataProvider = "validGroups")
    void groupMod(GroupData group) {
        Groups before = app.db().groups();
        GroupData modGroupe = before.iterator().next();
        group.withId(modGroupe.getId());
        app.group().modification(group);
        app.goTo().groupPage();
        assertEquals(app.group().count(), before.size());
        Groups after = app.db().groups();
        assertThat(after, equalToObject(before.without(modGroupe).withAdded(group)));
    }
}
