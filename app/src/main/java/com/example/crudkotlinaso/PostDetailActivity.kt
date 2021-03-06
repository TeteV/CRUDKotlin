 package com.example.crudkotlinaso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.crudkotlinaso.models.Post
import com.example.crudkotlinaso.service.PostServiceImpl

 class PostDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        listeners()
    }

     private fun listeners(){
         val buscarBtn = findViewById<Button>(R.id.BuscarBtn)
         buscarBtn.setOnClickListener {
             val id: Int = findViewById<TextView>(R.id.idTxt).text.toString().toInt()
             Log.v("Hola","Clickao")
             getById(id)
         }

         val volverBtn = findViewById<Button>(R.id.VolverBtn)
         volverBtn.setOnClickListener {
             val intent = Intent(this,MainWindow::class.java)
             startActivity(intent)
         }

         val deleleBtn = findViewById<Button>(R.id.DeleteBtn)
         deleleBtn.setOnClickListener {
             val id: Int = findViewById<TextView>(R.id.idTxt).text.toString().toInt()
             deletePost(id)
         }

         val updateBtn = findViewById<Button>(R.id.EditBtn)
         updateBtn.setOnClickListener {
             val id: Int = findViewById<TextView>(R.id.idTxt).text.toString().toInt()
             val title: String = findViewById<TextView>(R.id.editTextTitle).text.toString()
             val body: String = findViewById<TextView>(R.id.editTextBody).text.toString()

             val post = Post(id,title,body)
             updatePost(post)
         }
     }


    private fun getById(id:Int) {
        val postServiceImpl = PostServiceImpl()
        postServiceImpl.getById(this, id) { response ->
            run {
                Log.v("Hola","Paso por aqui")
                val txt_title: TextView = findViewById(R.id.editTextTitle)
                val txt_body : TextView = findViewById(R.id.editTextBody)

                txt_title.setText(response?.title ?: "")
                txt_body.setText(response?.body ?: "")

                Log.v("Hola",response?.title ?: "")
                Log.v("Hola","Se terrmino")
            }
        }
    }

     private fun deletePost(postId: Int) {
         val postServiceImpl = PostServiceImpl()
         postServiceImpl.deletePost(this, postId) { ->
             run {
                 Log.v("Link", "Aqui iria al list")
                 /*val intent = Intent(this, PostListActivity::class.java)
                 startActivity(intent)*/
             }
         }
     }

     private fun updatePost(post: Post) {
         val postServiceImpl = PostServiceImpl()
         postServiceImpl.updatePost(this, post) { ->
             run {
                 Log.v("Link", "Iria al List")
                 /*val intent = Intent(this, PostDetailActivity::class.java)
                 startActivity(intent)*/
             }
         }
     }







}