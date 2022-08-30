package net.nemesis.contacts.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MsgResponse {

    @Expose
    @SerializedName("message")
    var message: String = ""
}