package net.nemesis.contacts.services

import com.google.gson.GsonBuilder
import net.nemesis.contacts.model.Contact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

typealias ResponseSuccess<T> = (T) -> Unit
typealias ResponseFail = (error: String) -> Unit

object ServicesManager {

    private fun create(): AppServices {

        val gsonBuilder = GsonBuilder()
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()

        val builder = Retrofit.Builder()
        builder.baseUrl(Endpoints.BASE)
        builder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))

        val retrofit = builder.build()
        return retrofit.create(AppServices::class.java)
    }

    fun requestContacts(success: ResponseSuccess<List<Contact>>, fail: ResponseFail) {
        val call = create().getContacts()
        call.enqueue(object : Callback<ArrayList<Contact>> {

            override fun onResponse(
                call: Call<ArrayList<Contact>>,
                response: Response<ArrayList<Contact>>
            ) {
//                val contacts = response.body()
//                if (contacts != null) {
//                    success(contacts)
//                } else {
//                    fail("Empty List")
//                }

                response.body()?.let { success(it) } ?: success(ArrayList())
            }

            override fun onFailure(call: Call<ArrayList<Contact>>, t: Throwable) {
                fail(t.localizedMessage ?: "Something went wrong")
            }

        })
    }

}