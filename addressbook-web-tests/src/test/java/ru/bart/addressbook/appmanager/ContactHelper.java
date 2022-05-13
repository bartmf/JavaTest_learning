package ru.bart.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.bart.addressbook.model.ContactData;
import ru.bart.addressbook.model.Contacts;
import ru.bart.addressbook.model.GroupData;

import java.io.File;
import java.util.List;

public class ContactHelper extends HelperBase {
    Contacts cashContacts = null;
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void edit(ContactData user) {
        initUserModificationById(user.getId());
        //click(By.xpath("//img[@alt='Edit']"));
        fillInfo(user);
        click(By.name("update"));
        cashContacts = null;
        click(By.xpath("//a[contains(text(),'home page')]"));

    }

    private void initUserModificationById(int id) {
        click(By.xpath("//a[@href='edit.php?id=" + id +"']"));
    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void delete(ContactData user) {
        selectById(user.getId());
        click(By.xpath("//input[@value='Delete']"));
        cashContacts = null;
    }

    public ContactData infoFromEditForm(ContactData user) {
        initUserModificationById(user.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();//
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String fax = wd.findElement(By.name("work")).getAttribute("fax");
        wd.navigate().back();
        return new ContactData().withId(user.getId()).withName(firstName).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email)
                .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public void fillInfo(ContactData userData) {
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
        attach(userData.getPhoto(), "photo");
    }

    private void type(String userData, String locator) {
        if (userData != null) {
            wd.findElement(By.name(locator)).click();
            wd.findElement(By.name(locator)).clear();
            wd.findElement(By.name(locator)).sendKeys(userData);
        }
    }

    private void attach(File file, String locator) {
        if (file != null) {
            wd.findElement(By.name(locator)).sendKeys(file.getAbsolutePath());
        }
    }
    public void create(ContactData userData) {
        click(By.linkText("add new"));
        fillInfo(userData);
        click(By.name("submit"));
        cashContacts = null;
        click(By.xpath("//a[contains(text(),'home page')]"));
        //wd.findElement(By.xpath("//input[21]")).click();
    }

    public Contacts all() {
        if(cashContacts != null){
            return new Contacts(cashContacts);
        }
        cashContacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> columns = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(columns.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String lastName = columns.get(1).getText();
            String firstName = columns.get(2).getText();
            String allAddress = columns.get(3).getText();
            String allEmails = columns.get(4).getText();
            String allPhones = columns.get(5).getText();
            cashContacts.add(new ContactData().withId(id).withName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones).withAllAddrress(allAddress).withAllEmails(allEmails));
        }
        return new Contacts(cashContacts);
    }

    public int count() {
        return wd.findElements(By.name(("selected[]"))).size();
    }

    public void changeGroupToAdd(GroupData group, ContactData contact) {
        selectById(contact.getId());
        changeGroupForAdd(group.getId());
        submitChangeGroup();
    }

    public void changeGroupForAdd(int index) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.format("%s", index));
    }

    public void submitChangeGroup() {
        click(By.xpath("//input[@type='submit']"));
    }
    public void changeGroupToDel(GroupData group, ContactData contact) {
        changeGroupForDel(group.getId());
        selectById(contact.getId());
        submitRemoveFromGroup();
    }


    public void submitRemoveFromGroup() {
        click(By.xpath("//input[@name='remove']"));
    }

    public void changeGroupForDel(int index) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.format("%s", index));
    }
}
