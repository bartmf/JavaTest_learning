package ru.bart.addressbook.tests;

import org.testng.annotations.Test;
import ru.bart.addressbook.model.UserData;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreationTests() throws Exception {
    appMan.getNavigationHelper().godoCreateNewUser();
    appMan.getUserHelper().creationUser(new UserData("TestName", "TestMiddleName", "TestLastName",
            "TestNickName", "TestTitle", "TestCompany",
            "Test Address, Test address 2", "+7686809", "+5467890", "+345678",
            "+3456789", "test@test.te"));
  }
}
