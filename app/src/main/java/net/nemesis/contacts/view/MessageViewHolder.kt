package net.nemesis.contacts.view

import androidx.recyclerview.widget.RecyclerView
import net.nemesis.contacts.databinding.ItemMessageBinding
import net.nemesis.contacts.model.Message
import net.nemesis.contacts.utils.toFormat

class MessageViewHolder(private val binding: ItemMessageBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindData(message: Message) {
        with(binding) {
            messageDate.text = message.date.toFormat("dd MMMM yyyy")
            messageText.text = message.message
        }
    }

}