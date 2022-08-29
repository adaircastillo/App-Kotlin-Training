package net.nemesis.contacts

import android.app.Application
import net.nemesis.contacts.model.Repository

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Repository.initialize(applicationContext)
    }

}