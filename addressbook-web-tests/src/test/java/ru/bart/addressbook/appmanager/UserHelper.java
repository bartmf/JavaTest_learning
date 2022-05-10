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
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(userData.getName());
      wd.findElement(By.name("middlename")).click();
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(userData.getMiddleName());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(userData.getLastName());
      wd.findElement(By.name("nickname")).click();
      wd.findElement(By.name("nickname")).clear();
      wd.findElement(By.name("nickname")).sendKeys(userData.getNickName());
      wd.findElement(By.name("title")).click();
      wd.findElement(By.name("title")).clear();
      wd.findElement(By.name("title")).sendKeys(userData.getTitle());
      wd.findElement(By.name("company")).click();
      wd.findElement(By.name("company")).clear();
      wd.findElement(By.name("company")).sendKeys(userData.getCompany());
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(userData.getAddress());
      wd.findElement(By.name("home")).click();
      wd.findElement(By.name("home")).clear();
      wd.findElement(By.name("home")).sendKeys(userData.getHomePhone());
      //wd.findElement(By.name("mobile")).click();
      //wd.findElement(By.name("mobile")).clear();
      //wd.findElement(By.name("mobile")).sendKeys(userData.getMobilePhone());
      wd.findElement(By.name("work")).click();
      wd.findElement(By.name("work")).clear();
      wd.findElement(By.name("work")).sendKeys(userData.getWorkPhone());
      wd.findElement(By.name("fax")).click();
      wd.findElement(By.name("fax")).clear();
      wd.findElement(By.name("fax")).sendKeys(userData.getFax());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(userData.getEmail());
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
            UserData user = new UserData(id, firstName, lastName);
            users.add(user);
        }
        return users;
    }
}
