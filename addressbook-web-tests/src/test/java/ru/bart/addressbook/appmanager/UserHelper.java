package ru.bart.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.bart.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserHelper extends HelperBase{

  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void editUser(){
      //wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[3]/td[8]/a/img")).click();
      click(By.xpath("//img[@alt='Edit']"));
  }

  public void selectUser(){click(By.name("selected[]"));}
  public void delUser(){click(By.xpath("//input[@value='Delete']"));}


  public void fillInfoNewUser(UserData userData) {
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
        if(userData != null) {
            wd.findElement(By.name(locator)).click();
            wd.findElement(By.name(locator)).clear();
            wd.findElement(By.name(locator)).sendKeys(userData);
        }
    }

    public boolean isThereUser(){
      return isElementPresent(By.name("selected[]"));
    }

    public void creationUser(UserData userData) {
            fillInfoNewUser(userData);
            click(By.name("submit"));
            click(By.xpath("//a[contains(text(),'home page')]"));
            //wd.findElement(By.xpath("//input[21]")).click();
    }

    public int getUserCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<UserData> getUserList() {
        List<UserData> users = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        //List<WebElement> elements = wd.findElements(By.tagName("tr"));
        for (WebElement element : elements){
            List<WebElement> columns = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(columns.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String firstName = columns.get(2).getText();
            String lastName = columns.get(1).getText();
            UserData user = new UserData().withId(id).withName(firstName).withLastName(lastName);
            users.add(user);
        }
        return users;
    }
}
