package com.example.crudkotlinaso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainWindow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_window)

        listeners()
    }

    fun listeners(){
        val botonAll = findViewById<Button>(R.id.GoToAllBtn)
        botonAll.setOnClickListener {
            val intent = Intent(this,PostListActivity::class.java)
            startActivity(intent)
        }

        val botonOne = findViewById<Button>(R.id.GoToOneBtn)
        botonOne.setOnClickListener {
            val intent = Intent(this,PostDetailActivity::class.java)
            startActivity(intent)
        }

        val botonGoToCreate = findViewById<Button>(R.id.GoToCreateBtn)
        botonGoToCreate.setOnClickListener {
            val intent = Intent(this,CreatePost::class.java)
            startActivity(intent)
        }
    }
}