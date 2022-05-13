package ru.bart.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.bart.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class RegistrationTest extends TestBase{

    @BeforeMethod
    public void startMailserver() {
        app.mail().start();
    }
    @Test
    public void testRegistration() throws MessagingException, IOException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String user = String.format("user%s", now);
        String password = "password";
        app.registrations().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmatationLink = findConfirmationLink(mailMessages, email);
        app.registrations().finish(confirmatationLink, password);
        assertTrue(app.newSession().login(user, password));

    }

    private String  findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod (alwaysRun = true)
    public void stopMailserver() {
        app.mail().stop();
    }
}