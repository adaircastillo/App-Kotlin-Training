package net.nemesis.contacts.services

import net.nemesis.contacts.model.Contact

typealias Completed<T> = (completed: RepositoryData<T>) -> Unit

sealed class RepositoryData<out T> {
    data class Success<T>(val data: T): RepositoryData<T>()
    data class Fail(val errorMessage: String): RepositoryData<Nothing>()
}

class Repository {

    companion object {

        fun getContacts(completed: Completed<List<Contact>>){
            ServicesManager.requestContacts(success = {
                completed(RepositoryData.Success(it))
            }, fail = {
                completed(RepositoryData.Fail(it))
            })
        }

    }

}