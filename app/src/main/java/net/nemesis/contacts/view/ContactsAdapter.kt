package net.nemesis.contacts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.nemesis.contacts.databinding.ItemContactBinding
import net.nemesis.contacts.model.Contact

class ContactsAdapter(val contacts: ArrayList<Contact>, val listener: ContactsListener?): RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bindData(contact, listener)
    }

    override fun getItemCount(): Int = contacts.size
}