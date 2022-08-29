package net.nemesis.contacts.model.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import net.nemesis.contacts.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getContactDao(): ContactDAO

}