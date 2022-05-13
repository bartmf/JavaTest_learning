package ru.bart.mantis.appmanger;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }
    public void start(String username, String password, int modifiedUser) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
        wd.findElement(By.xpath(String.format("//a[@href='manage_user_edit_page.php?user_id=%s']", modifiedUser))).click();
        click(By.cssSelector("form#manage-user-reset-form>fieldset>span>input"));
    }

    public void finish(String confirmatationLink, String password) {
        wd.get(confirmatationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
