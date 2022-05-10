package ru.bart.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.bart.addressbook.model.GroupData;
import ru.bart.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void initGroupModif(){
        click(By.name("edit"));
    }
    public void updateGroup(){
        click(By.name("update"));
    }


    public void selectById(int id){ wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();}

    public void delete(GroupData group){
        selectById(group.getId());
        click(By.name("delete"));
        returnToPage();
    }

    public void returnToPage() {
        click(By.xpath("//a[contains(text(),'group page')]"));
    }
    public void create(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
    }

    public void modification(GroupData group) {
        selectById(group.getId());
        initGroupModif();
        fillGroupForm(group);
        updateGroup();
    }

    public Groups all() {
        Groups groups = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
