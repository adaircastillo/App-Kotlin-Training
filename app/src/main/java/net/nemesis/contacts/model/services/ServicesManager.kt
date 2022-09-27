package net.nemesis.contacts.model.services

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.nemesis.contacts.model.Message
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException

typealias ResponseSuccess<T> = (T) -> Unit
typealias ResponseFail = (error: String) -> Unit

class ServicesManager private constructor(context: Context) {

    private val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isInternetAvailable(): Boolean {
        val networkInfo = manager.activeNetworkInfo
        if(networkInfo != null && networkInfo.isConnected){
            return true
        }
        return false
    }

    private fun create(urlBase: String = Endpoints.BASE): AppServices {

        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat("yyyy-MM-dd")
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()

        val builder = Retrofit.Builder()
        builder.baseUrl(urlBase)
        builder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))

        val retrofit = builder.build()
        return retrofit.create(AppServices::class.java)
    }

    suspend fun requestContacts() = create().getContacts()

    suspend fun sendMessage(message: Message) = create(Endpoints.BASE_MESSAGES).sendMessage(message)

    val messages: Flow<List<Message>> = flow {
        while(true) {
            val data = create(Endpoints.BASE_MESSAGES).getMessages()
            emit(data)
            delay(5000)
        }
    }

    companion object {
        private var INSTANCE: ServicesManager? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = ServicesManager(context)
            }
        }

        fun get(): ServicesManager = INSTANCE ?: throw IllegalStateException("ServicesManager must be initialized")
    }

}