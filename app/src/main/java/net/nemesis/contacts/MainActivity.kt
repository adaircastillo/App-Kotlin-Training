package net.nemesis.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.commit
import net.nemesis.contacts.model.Contact
import net.nemesis.contacts.view.ContactDetailFragment
import net.nemesis.contacts.view.ContactsFragment
import net.nemesis.contacts.view.ContactsListener
import net.nemesis.contacts.view.MessagesFragment

class MainActivity : AppCompatActivity(), ContactsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactsFragment = ContactsFragment()
        supportFragmentManager.commit { replace(R.id.fragment_frame, contactsFragment) }
    }

    override fun makeACallTo(contact: Contact) {
        Toast.makeText(this, contact.phone, Toast.LENGTH_SHORT).show()
    }

    override fun sendEmailTo(contact: Contact) {
        supportFragmentManager.commit {
            add(R.id.fragment_frame, MessagesFragment.newInstance(contact.idContact))
            addToBackStack(null)
        }

//        Toast.makeText(this, contact.email, Toast.LENGTH_SHORT).show()
    }

    override fun onContactSelected(contact: Contact) {
        supportFragmentManager.commit {
            add(R.id.fragment_frame, ContactDetailFragment.newInstance(contact.idContact))
            addToBackStack(null)
        }

    }
}