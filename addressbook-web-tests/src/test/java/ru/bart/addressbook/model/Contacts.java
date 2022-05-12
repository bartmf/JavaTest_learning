package ru.bart.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {
    private Set<ContactData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate);

    }
    public Contacts(Collection<ContactData> contacts) {
        this.delegate = new HashSet<ContactData>(contacts);
    }

    public Contacts() {
        this.delegate = new HashSet<>();
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }
    public Contacts withAdded(ContactData user){
        Contacts contacts = new Contacts(this);
        contacts.add(user);
        return contacts;
    }
    public Contacts without(ContactData user){
        Contacts contacts = new Contacts(this);
        contacts.remove(user);
        return contacts;
    }
}
