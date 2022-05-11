package ru.bart.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.bart.addressbook.model.UserData;
import ru.bart.addressbook.model.Users;

import java.util.List;

public class UserHelper extends HelperBase {
    Users cashUsers = null;
    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void edit(UserData user) {
        click(By.xpath("//a[@href='edit.php?id=" + user.getId() +"']"));
        //click(By.xpath("//img[@alt='Edit']"));
        fillInfo(user);
        click(By.name("update"));
        cashUsers = null;
        click(By.xpath("//a[contains(text(),'home page')]"));

    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void delete(UserData user) {
        selectById(user.getId());
        click(By.xpath("//input[@value='Delete']"));
        cashUsers = null;
    }

    public void fillInfo(UserData userData) {
        type(userData.getName(), "firstname");
        type(userData.getMiddleName(), "middlename");
        type(userData.getLastName(), "lastname");
        type(userData.getNickName(), "nickname");
        type(userData.getTitle(), "title");
        type(userData.getCompany(), "company");
        type(userData.getAddress(), "address");
        type(userData.getHomePhone(), "home");
        type(userData.getMobilePhone(), "mobile");
        type(userData.getWorkPhone(), "work");
        type(userData.getFax(), "fax");
        type(userData.getEmail(), "email");
    }

    private void type(String userData, String locator) {
        if (userData != null) {
            wd.findElement(By.name(locator)).click();
            wd.findElement(By.name(locator)).clear();
            wd.findElement(By.name(locator)).sendKeys(userData);
        }
    }


    public void create(UserData userData) {
        click(By.linkText("add new"));
        fillInfo(userData);
        click(By.name("submit"));
        cashUsers = null;
        click(By.xpath("//a[contains(text(),'home page')]"));
        //wd.findElement(By.xpath("//input[21]")).click();
    }

    public Users all() {
        if(cashUsers != null){
            return new Users(cashUsers);
        }
        cashUsers = new Users();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> columns = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(columns.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String firstName = columns.get(2).getText();
            String lastName = columns.get(1).getText();
            UserData user = new UserData().withId(id).withName(firstName).withLastName(lastName);
            cashUsers.add(user);
        }
        return new Users(cashUsers);
    }
}
