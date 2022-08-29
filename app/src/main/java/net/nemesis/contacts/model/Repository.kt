package net.nemesis.contacts.model

import android.content.Context
import net.nemesis.contacts.model.persistence.PersistenceManager
import net.nemesis.contacts.model.services.ServicesManager
import java.lang.IllegalStateException
import java.net.ServerSocket

typealias Completed<T> = (completed: RepositoryData<T>) -> Unit

sealed class RepositoryData<out T> {
    data class Success<T>(val data: T): RepositoryData<T>()
    data class Fail(val errorMessage: String): RepositoryData<Nothing>()
}

class Repository private constructor() {

    private val persistence = PersistenceManager.get()

    companion object {

        private var INSTANCE: Repository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                PersistenceManager.initialize(context)
                INSTANCE = Repository()
            }
        }

        fun get(): Repository = INSTANCE ?: throw IllegalStateException("Repository must be initialized")
    }

    suspend fun getContacts(context: Context): RepositoryData<List<Contact>> {
        return try {
            if(ServicesManager.isInternetAvailable(context)) {
                val contacts = ServicesManager.requestContacts()
                RepositoryData.Success(contacts)
            }else {
                val contacts = persistence.getContacts()
                RepositoryData.Success(contacts)
            }
        }catch (ex: Exception) {
            RepositoryData.Fail(ex.localizedMessage ?: "")
        }
    }

    fun getSingleContact(id: Int, completed: Completed<Contact?>) {
        persistence.getSingleContact(id) {
            completed(RepositoryData.Success(it))
        }
    }

}