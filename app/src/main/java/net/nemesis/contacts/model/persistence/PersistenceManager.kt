package net.nemesis.contacts.model.persistence

import android.content.Context
import androidx.room.Room
import net.nemesis.contacts.model.Contact
import net.nemesis.contacts.model.services.ResponseSuccess
import java.lang.IllegalStateException
import java.util.concurrent.Executors

class PersistenceManager private constructor(context: Context) {

    private val database: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app-database").build()

    private val executor = Executors.newSingleThreadExecutor()

    suspend fun getContacts() = database.getContactDao().getAll()

    fun getSingleContact(id: Int, success: ResponseSuccess<Contact?>){
        executor.execute {
            success(database.getContactDao().getSingle(id))
        }
    }

    suspend fun insertContacts(contacts: Array<Contact> ) {
        database.getContactDao().insertAll(*contacts)
    }

    companion object {

        private var INSTANCE: PersistenceManager? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = PersistenceManager(context)
            }
        }

        fun get(): PersistenceManager = INSTANCE ?: throw IllegalStateException("Repository must be initialized")

    }

}