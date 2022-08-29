package net.nemesis.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.nemesis.contacts.model.ContactInfo
import net.nemesis.contacts.model.Repository
import net.nemesis.contacts.model.RepositoryData
import net.nemesis.contacts.model.toInfo

class ContactDetailViewModel: ViewModel() {

    private val repository = Repository.get()

    var contactLiveData = MutableLiveData<ContactInfo>()
    var errorLiveData = MutableLiveData<String>()

    fun loadContact(id: Int) {
        repository.getSingleContact(id) { res ->
            when(res) {
                is RepositoryData.Success -> {
                    res.data?.let {
                        contactLiveData.postValue(it.toInfo())
                    }
                }
                is RepositoryData.Fail -> {
                    errorLiveData.postValue(res.errorMessage)
                }
            }
        }
    }

}