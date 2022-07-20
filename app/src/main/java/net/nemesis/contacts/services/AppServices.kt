package net.nemesis.contacts.services

import net.nemesis.contacts.model.Contact
import retrofit2.Call
import retrofit2.http.POST

interface AppServices {

    @POST("demos/contacts.json")
    fun getContacts(): Call<ArrayList<Contact>>

}