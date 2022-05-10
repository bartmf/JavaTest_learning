package ru.bart.addressbook.model;

import java.util.Objects;

public class UserData {
    private int id = Integer.MAX_VALUE;
    private final String name;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String title;
    private final String company;
    private final String address;
    private final String homePhone;
    private final String mobilePhone;
    private final String workPhone;
    private final String fax;
    private final String email;

    public UserData(String name, String middleName, String lastName, String nickName, String title, String company,
                    String address, String homePhone, String mobilePhone, String workPhone, String fax, String email) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.fax = fax;
        this.email = email;
    }

    public UserData(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.middleName = "middleName";
        this.lastName = lastName;
        this.nickName = "nickName";
        this.title = "title";
        this.company = "company";
        this.address = "address";
        this.homePhone = "homePhone";
        this.mobilePhone = "mobilePhone";
        this.workPhone = "workPhone";
        this.fax = "fax";
        this.email = "email";
    }

    public UserData(String name, String lastName) {
        this.name = name;
        this.middleName = "middleName";
        this.lastName = lastName;
        this.nickName = "nickName";
        this.title = "title";
        this.company = "company";
        this.address = "address";
        this.homePhone = "homePhone";
        this.mobilePhone = "mobilePhone";
        this.workPhone = "workPhone";
        this.fax = "fax";
        this.email = "email";
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName'" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(name, userData.name) && Objects.equals(lastName, userData.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }
}
