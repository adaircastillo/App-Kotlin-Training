package net.nemesis.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import net.nemesis.contacts.controller.ContactsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactsFragment = ContactsFragment()
        supportFragmentManager.commit { replace(R.id.fragment_frame, contactsFragment) }
    }
}