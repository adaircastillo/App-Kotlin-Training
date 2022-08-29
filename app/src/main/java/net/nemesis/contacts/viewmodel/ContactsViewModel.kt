package net.nemesis.contacts.viewmodel

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.nemesis.contacts.model.Contact
import net.nemesis.contacts.model.Repository
import net.nemesis.contacts.model.RepositoryData

class ContactsViewModel: ViewModel() {


    private val repository = Repository.get()

    lateinit var context: Context

    val contactsLiveData: LiveData<List<Contact>> = liveData {
        when(val result = repository.getContacts(context)) {
            is RepositoryData.Success -> emit(result.data)
            is RepositoryData.Fail -> errorLiveData.postValue(result.errorMessage)
        }
    }

    val errorLiveData: MutableLiveData<String> = MutableLiveData()

//    fun requestContacts(context: Context){
//        viewModelScope.launch {
//            when(val result = repository.getContacts(context)) {
//                is RepositoryData.Success -> contactsLiveData.postValue(result.data)
//                is RepositoryData.Fail -> errorLiveData.postValue(result.errorMessage)
//            }
//        }
//    }

}