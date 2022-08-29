package net.nemesis.contacts.model.services

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import net.nemesis.contacts.model.Contact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

typealias ResponseSuccess<T> = (T) -> Unit
typealias ResponseFail = (error: String) -> Unit

object ServicesManager {

    fun isInternetAvailable(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if(networkInfo != null && networkInfo.isConnected){
            return true
        }
        return false
    }

    private fun create(): AppServices {

        val gsonBuilder = GsonBuilder()
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()

        val builder = Retrofit.Builder()
        builder.baseUrl(Endpoints.BASE)
        builder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))

        val retrofit = builder.build()
        return retrofit.create(AppServices::class.java)
    }

    suspend fun requestContacts() = create().getContacts()

}