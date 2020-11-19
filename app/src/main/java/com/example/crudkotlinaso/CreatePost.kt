package com.example.crudkotlinaso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.crudkotlinaso.models.Post
import com.example.crudkotlinaso.service.PostServiceImpl

class CreatePost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        listeners()
    }

    fun listeners(){
        val backBtn = findViewById<Button>(R.id.BackBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, MainWindow::class.java)
            startActivity(intent)
        }

        val createBtn = findViewById<Button>(R.id.CreateBtn)
        createBtn.setOnClickListener {
            Log.v("Create","Picao en creao")
            //val id: Int = findViewById<TextView>(R.id.idTxt).text.toString().toInt()
            val id = this.intent.getIntExtra("postId", 0)
            val title: String = findViewById<TextView>(R.id.editTextAddTitle).text.toString()
            val body: String = findViewById<TextView>(R.id.editTextAddBody).text.toString()

            val post = Post(id,title,body)
            Log.v("Create", post.toString())
            createPost(post)
        }
    }

    private fun createPost(post: Post) {
        val postServiceImpl = PostServiceImpl()
        postServiceImpl.createPost(this, post) { ->
            run {
               /* val intent = Intent(this, BicycleListActivity::class.java)
                startActivity(intent)*/
                Log.v("Create","Creado")
            }
        }
    }
}