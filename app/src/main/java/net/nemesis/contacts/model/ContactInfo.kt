package net.nemesis.contacts.model

data class ContactInfo(val photo:String, val name: String, val job: String, val phone: String)

fun Contact.toInfo(): ContactInfo {
    return ContactInfo(photo, name, job, phone)
}
