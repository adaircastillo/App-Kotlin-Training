package net.nemesis.contacts.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Message {

    @Expose
    @SerializedName("id")
    var idMessage: Int = 0

    @Expose
    @SerializedName("id_contact")
    var idContact: Int = 0

    @Expose
    @SerializedName("message")
    var message: String = ""

    @Expose
    @SerializedName("date")
    var date: Date = Date()

}