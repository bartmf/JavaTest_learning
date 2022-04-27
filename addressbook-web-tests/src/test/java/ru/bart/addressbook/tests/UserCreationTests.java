package ru.bart.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreationTests() throws Exception {
    applicationManager.getNavigationHelper().godoCreateNewUser();
    applicationManager.getUserHelper().fillInfoNewUser(new UserData("TestName", "TestMiddleName", "TestLastName",
            "TestNickName", "TestTitle", "TestCompany",
            "Test Address, Test address 2", "+7686809", "+5467890", "+345678",
            "+3456789", "test@test.te"));
    applicationManager.wd.findElement(By.xpath("//input[21]")).click();
  }
}
