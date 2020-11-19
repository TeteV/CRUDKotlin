package com.example.crudkotlinaso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class PostListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        listeners()
    }

    private fun listeners(){

        val volverBtn = findViewById<Button>(R.id.VolverBtn)
        volverBtn.setOnClickListener {
            val intent = Intent(this,MainWindow::class.java)
            startActivity(intent)
        }
    }
}