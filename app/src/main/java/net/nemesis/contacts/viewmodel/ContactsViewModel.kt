package net.nemesis.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.nemesis.contacts.model.Contact
import net.nemesis.contacts.services.*

class ContactsViewModel: ViewModel() {

    val contactsLiveData: MutableLiveData<List<Contact>> = MutableLiveData()

    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun requestContacts(){
        Repository.getContacts {
            when(it) {
                is RepositoryData.Success -> contactsLiveData.postValue(it.data)
                is RepositoryData.Fail -> errorLiveData.postValue(it.errorMessage)
            }
        }
    }

}