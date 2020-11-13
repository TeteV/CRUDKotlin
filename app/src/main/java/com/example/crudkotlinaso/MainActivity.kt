package com.example.crudkotlinaso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Response
import com.example.crudkotlinaso.models.Post
import com.example.crudkotlinaso.service.PostServiceImpl
import java.net.CacheResponse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getById()
    }

    fun getById() {
        val postServiceImpl = PostServiceImpl()
        postServiceImpl.getById(this, 1) { response ->
            run {
                Log.v("Hola","Paso por aqui")
                val txt_title: TextView = findViewById(R.id.textViewTitle)
                val txt_body : TextView = findViewById(R.id.textViewBody)

               /* val myTitleRes: String = response?.title
                val myBodyRes: String = response?.body*/
                txt_title.setText(response?.title ?: "")
                txt_body.setText(response?.body ?: "")

                Log.v("Hola",response?.title ?: "")
                Log.v("Hola","Se terrmino")
               /* txt_title.setText(myTitleRes ? myTitleRes : "Code")
                txt_body.setText(myBodyRes)*/


            }
        }
    }


}