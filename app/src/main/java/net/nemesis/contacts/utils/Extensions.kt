package net.nemesis.contacts.utils

import net.nemesis.contacts.model.Contact
import okhttp3.internal.format
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

fun Contact.getPhotoUrl(): URI {
    return URI(photo)
}

fun Date.toFormat(format: String): String {
    val formatter = SimpleDateFormat(format, Locale.ENGLISH)
    return formatter.format(this)
}
