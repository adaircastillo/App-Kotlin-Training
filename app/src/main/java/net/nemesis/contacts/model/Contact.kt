package net.nemesis.contacts.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Contact {

    @Expose
    @SerializedName("id")
    var idContact: Int = 0

    @Expose
    @SerializedName("name")
    var name: String = ""

    @Expose
    @SerializedName("work")
    var job: String = ""

    @Expose
    @SerializedName("phone")
    var phone: String = ""

    @Expose
    @SerializedName("email")
    var email: String = ""

    @Expose
    @SerializedName("photo")
    var photo: String = ""

    var another: String = ""

    companion object {

        fun getContacts(): ArrayList<Contact> {
            val contacts = ArrayList<Contact>()

            val contact01 = Contact()
            contact01.idContact = 1
            contact01.name = "Juan Perez"
            contact01.job = "Developer"
            contact01.phone = "2244556677"
            contact01.email = "juanito_power_123@hotmail.com"
            contact01.photo = "https://miro.medium.com/max/2048/0*0fClPmIScV5pTLoE.jpg"
            contacts.add(contact01)

            val contact02 = Contact()
            contact02.idContact = 1
            contact02.name = "Adolfo Maurio"
            contact02.job = "iOS Developer"
            contact02.phone = "456788"
            contact02.email = "adolfo@gmail.com"
            contact02.photo = "https://miro.medium.com/max/2048/0*0fClPmIScV5pTLoE.jpg"
            contacts.add(contact02)

            return contacts
        }

    }

}