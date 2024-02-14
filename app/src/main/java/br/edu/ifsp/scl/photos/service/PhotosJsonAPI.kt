package br.edu.ifsp.scl.photos.service

import android.content.Context
import br.edu.ifsp.scl.photos.model.PhotosList
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.net.HttpURLConnection

class PhotosJsonAPI(context: Context) {

    companion object{
        @Volatile
        private var INSTANCE: PhotosJsonAPI? = null

        const val ENDPOINT = "https://jsonplaceholder.typicode.com/photos/"

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this){
            INSTANCE ?: PhotosJsonAPI(context).also {
                INSTANCE = it
            }
        }
    }

    private val requestQueue: RequestQueue by lazy{
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(request: Request<T>){
        requestQueue.add(request)
    }

    class PhotosListRequest(
        private val responseListener: Response.Listener<PhotosList>,
        errorListener: Response.ErrorListener
    ): Request<PhotosList>(Method.GET, ENDPOINT, errorListener){
        override fun parseNetworkResponse(response: NetworkResponse?): Response<PhotosList> {
            return if(response?.statusCode == HttpURLConnection.HTTP_OK || response?.statusCode == HttpURLConnection.HTTP_NOT_MODIFIED){
                String(response.data).run{
                    Response.success(
                        Gson().fromJson(this, PhotosList::class.java),
                        HttpHeaderParser.parseCacheHeaders(response)
                    )
                }
            } else{
                Response.error(VolleyError())
            }
        }

        override fun deliverResponse(response: PhotosList?) {
            responseListener.onResponse(response)
        }

    }

}