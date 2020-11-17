package com.example.crudkotlinaso.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.crudkotlinaso.models.Post

class PostServiceImpl : IPostService {

    override fun getById(context: Context, postId: Int, completionHandler: (response: Post?) -> Unit) {
        val path = PostSingleton.getInstance(context).baseUrl + "/api/posts/" + postId
        val objectRequest = JsonObjectRequest(Request.Method.GET, path, null,
            { response ->
                if(response == null) completionHandler(null)

                Log.v("holi","dentro del getByid")
                Log.v("holi",response.toString())

                val requestedPost= response.getJSONObject("post")

                val id = requestedPost.getInt("id")
                val title = requestedPost.getString("title")
                val body = requestedPost.getString("body")

                val post = Post(id,title,body)
                completionHandler(post)
            },
            { error ->
                Log.v("holi","Error en getById")
                completionHandler(null)
            })
        PostSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

     fun getAll(context: Context, completionHandler: (response: ArrayList<Post>?) -> Unit) {
        val path = PostSingleton.getInstance(context).baseUrl + "/api/posts"
        val arrayRequest = JsonArrayRequest(
            Request.Method.GET, path, null,
            { response ->
                var posts: ArrayList<Post> = ArrayList()
                for (i in 0 until response.length()) {
                    val post = response.getJSONObject(i)
                    val id = post.getInt("id")
                    val title = post.getString("title")
                    val body = post.getString("body")
                    posts.add(Post(id, title, body))
                }
                completionHandler(posts)
            },
            { error ->
                completionHandler(ArrayList<Post>())
            })
        PostSingleton.getInstance(context).addToRequestQueue(arrayRequest)
    }

}