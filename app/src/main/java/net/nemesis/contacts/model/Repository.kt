package net.nemesis.contacts.model

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.nemesis.contacts.model.persistence.PersistenceManager
import net.nemesis.contacts.model.services.ServicesManager
import java.lang.IllegalStateException

typealias Completed<T> = (completed: RepositoryData<T>) -> Unit

sealed class RepositoryData<out T> {
    data class Success<T>(val data: T): RepositoryData<T>()
    data class Fail(val errorMessage: String): RepositoryData<Nothing>()
}

class Repository private constructor() {

    private val persistence = PersistenceManager.get()
    private val services = ServicesManager.get()

    companion object {

        private var INSTANCE: Repository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                PersistenceManager.initialize(context)
                ServicesManager.initialize(context)
                INSTANCE = Repository()
            }
        }

        fun get(): Repository = INSTANCE ?: throw IllegalStateException("Repository must be initialized")
    }

    suspend fun getContacts(): RepositoryData<List<Contact>> {
        return try {
            if(services.isInternetAvailable()) {
                val contacts = services.requestContacts()
                persistence.insertContacts(contacts.toTypedArray())
                RepositoryData.Success(contacts)
            }else {
                val contacts = persistence.getContacts()
                RepositoryData.Success(contacts)
            }
        }catch (ex: Exception) {
            RepositoryData.Fail(ex.localizedMessage ?: "")
        }
    }

    suspend fun sendMessage(message: Message): RepositoryData<MsgResponse> {
        return try {
            if(services.isInternetAvailable()) {
                val msg = services.sendMessage(message)
                RepositoryData.Success(msg)
            } else {
                RepositoryData.Fail("No Internet")
            }
        } catch (ex: Exception) {
            RepositoryData.Fail(ex.localizedMessage ?: "")
        }
    }

    fun getSingleContact(id: Int, completed: Completed<Contact?>) {
        persistence.getSingleContact(id) {
            completed(RepositoryData.Success(it))
        }
    }

    val allMessages: Flow<List<Message>> =
        services.messages.map { list ->
            list.sortedByDescending { it.date }
        }

    fun getMesssagesByContact(idContact: Int): Flow<List<Message>> =
        services.messages.map { list ->
            list
                .filter { it.idContact == idContact }
                .sortedByDescending { it.date }
        }








}