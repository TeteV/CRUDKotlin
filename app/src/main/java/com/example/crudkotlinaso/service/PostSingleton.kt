package com.example.crudkotlinaso.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class PostSingleton constructor(context: Context){
   val baseUrl = "http://192.168.203.80:8000" //clase
    //  val baseUrl = "http://192.168.1.54:8000" //casa

    companion object {
        @Volatile
        private var INSTANCE: PostSingleton? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: PostSingleton(context).also {
                    INSTANCE = it
                }
            }
    }

    val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}