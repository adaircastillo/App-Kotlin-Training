package net.nemesis.contacts.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import net.nemesis.contacts.model.Message
import net.nemesis.contacts.model.Repository

class MessagesViewModel: ViewModel() {

    private val repository = Repository.get()

    var idContact: Int = 0

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    val messagesLiveData: LiveData<List<Message>> = liveData {
        repository.getMesssagesByContact(idContact)
            .onStart {
                _isLoading.postValue(true)
            }
            .catch { ex ->
                _isLoading.postValue(false)
                errorLiveData.postValue(ex.localizedMessage)
            }
            .collectLatest {
                _isLoading.postValue(false)
                emit(it)
            }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            repository.sendMessage(message)
        }
    }


}