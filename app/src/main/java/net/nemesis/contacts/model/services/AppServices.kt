package net.nemesis.contacts.model.services

import net.nemesis.contacts.model.Contact
import net.nemesis.contacts.model.Message
import net.nemesis.contacts.model.MsgResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

interface AppServices {

    @GET("demos/contacts.json")
    suspend fun getContacts(): ArrayList<Contact>

    @GET("list.php")
    suspend fun getMessages(): List<Message>

    @POST("send_message.php")
    suspend fun sendMessage(@Body message: Message): MsgResponse

}