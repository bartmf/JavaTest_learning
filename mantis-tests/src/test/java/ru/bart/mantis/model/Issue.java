package ru.bart.mantis.model;

public class Issue {

    private int id;
    private String summar;
    private String description;
    private Project project;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummar() {
        return summar;
    }

    public Issue withSummar(String summar) {
        this.summar = summar;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }
}
