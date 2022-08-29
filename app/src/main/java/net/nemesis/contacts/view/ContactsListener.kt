package net.nemesis.contacts.view

import net.nemesis.contacts.model.Contact

interface ContactsListener {
    fun makeACallTo(contact: Contact){}
    fun sendEmailTo(contact: Contact){}
    fun onContactSelected(contact: Contact)
}