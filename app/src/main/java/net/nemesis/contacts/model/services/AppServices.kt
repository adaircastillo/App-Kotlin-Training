package net.nemesis.contacts.model.services

import net.nemesis.contacts.model.Contact
import retrofit2.http.POST
import java.util.*

interface AppServices {

    @POST("demos/contacts.json")
    suspend fun getContacts(): ArrayList<Contact>

}