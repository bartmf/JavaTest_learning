package ru.bart.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.bart.mantis.model.MailMessage;
import ru.bart.mantis.model.Users;
import ru.bart.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PassChangeTest extends TestBase{

    @BeforeMethod
    public void startMail() {
        app.mail().start();
    }


    @Test
    public void testChangePassword() throws MessagingException, IOException {
        Users before = app.db().users();
        String password = "10";
        UserData modifiedUser = before.iterator().next();
        if (modifiedUser.getId() == 1) {
            modifiedUser = before.withOut(modifiedUser).iterator().next();
        }
        app.changepassword().start("administrator", "root", modifiedUser.getId());

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmatationLink = findConfirmationLink(mailMessages, modifiedUser.getEmail());

        app.changepassword().finish(confirmatationLink, password);
        assertTrue(app.newSession().login(modifiedUser.getName(), password));



    }
    private String  findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMail() {
        app.mail().stop();
    }

}
