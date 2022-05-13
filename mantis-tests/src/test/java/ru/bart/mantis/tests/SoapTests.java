package ru.bart.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.bart.mantis.model.Issue;
import ru.bart.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {

    @Test
    public void testGetProject() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project:projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummar(("Test issue")).withDescription("Tst issue").withProject(projects.iterator().next());
        Issue create = app.soap().addIssue(issue);
        Assert.assertEquals(create.getSummar(), issue.getSummar());
    }
}
