package net.nemesis.contacts.controller

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.nemesis.contacts.databinding.ItemContactBinding
import net.nemesis.contacts.model.Contact
import net.nemesis.contacts.utils.capitalization
import net.nemesis.contacts.utils.getPhotoUrl
import net.nemesis.contacts.utils.toFormat
import java.util.*

class ContactViewHolder(private val binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindData(contact: Contact) {
        with(binding) {

            Glide.with(root)
                .load(contact.photo)
                .into(contactPhoto)

            contactName.text = contact.name.capitalization()
            contactJob.text = contact.job
            contactPhone.text = Date().toFormat("dd MM yyyy")

            contactEmailButton.setOnClickListener {

            }

            contactPhoneButton.setOnClickListener {

            }

        }
    }

}