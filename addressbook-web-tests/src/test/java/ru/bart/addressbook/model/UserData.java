package ru.bart.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class UserData {
    @Id
    @Column(name="id")
    private int id = Integer.MAX_VALUE;
    @Column(name="firstname")
    @Expose
    private String name;
    @Column(name="middlename")
    @Expose
    private String middleName;
    @Expose
    @Column(name="lastname")
    private String lastName;
    @Expose
    @Column(name="nickname")
    private String nickName;
    @Expose
    @Column(name="title")
    private String title;
    @Expose
    @Column(name="company")
    private String company;
    @Expose
    @Column(name="address")
    @Type(type = "text")
    private String address;
    transient private String allPhones;
    @Expose
    @Column(name="home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Column(name="mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Expose
    @Column(name="work")
    @Type(type = "text")
    private String workPhone;
    @Column(name="fax")
    @Type(type = "text")
    private String fax;
    @Expose
    @Column(name="email")
    @Type(type = "text")
    private String email;
    @Expose
    @Column(name="email2")
    @Type(type = "text")
    private String email2;
    @Expose
    @Column(name="email3")
    @Type(type = "text")
    private String email3;
    transient private String allEmails;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

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

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public UserData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public UserData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public UserData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public UserData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withName(String name) {
        this.name = name;
        return this;
    }

    public UserData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public UserData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public UserData withTitle(String title) {
        this.title = title;
        return this;
    }

    public UserData withCompany(String company) {
        this.company = company;
        return this;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public UserData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public UserData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public UserData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id && Objects.equals(name, userData.name) && Objects.equals(middleName, userData.middleName) && Objects.equals(lastName, userData.lastName) && Objects.equals(nickName, userData.nickName) && Objects.equals(title, userData.title) && Objects.equals(company, userData.company) && Objects.equals(address, userData.address) && Objects.equals(homePhone, userData.homePhone) && Objects.equals(mobilePhone, userData.mobilePhone) && Objects.equals(workPhone, userData.workPhone) && Objects.equals(fax, userData.fax) && Objects.equals(email, userData.email) && Objects.equals(email2, userData.email2) && Objects.equals(email3, userData.email3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, middleName, lastName, nickName, title, company, address, homePhone, mobilePhone, workPhone, fax, email, email2, email3);
    }
}
