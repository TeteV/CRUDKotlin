package com.example.crudkotlinaso.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.crudkotlinaso.models.Post
import org.json.JSONObject

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
                Log.v("holi",error.toString())
                completionHandler(null)
            })
        PostSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun deletePost(context: Context, postId: Int, completionHandler: () -> Unit) {
        val path = PostSingleton.getInstance(context).baseUrl + "/api/delete-post/" + postId
        val objectRequest = JsonObjectRequest(Request.Method.DELETE, path, null,
            { response ->
                Log.v("borro", "se borró")
                completionHandler()
            },
            { error ->
                Log.v("borro", "error al borrar")
                completionHandler()
            })
        PostSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun updatePost(context: Context, post: Post, completionHandler: () -> Unit) {
        val path = PostSingleton.getInstance(context).baseUrl + "/api/update-post/" + post.id
        val postJson: JSONObject = JSONObject()
        postJson.put("id", post.id.toString())
        postJson.put("title", post.title)
        postJson.put("body", post.body)

        val objectRequest = JsonObjectRequest(Request.Method.PUT, path, postJson,
            { response ->
                completionHandler()
            },
            { error ->
                completionHandler()
            })
        PostSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun createPost(context: Context, post: Post, completionHandler: () -> Unit) {
        val path = PostSingleton.getInstance(context).baseUrl + "/api/add-post"
        val postJson: JSONObject = JSONObject()
        postJson.put("id", post.id.toString())
        postJson.put("title", post.title)
        postJson.put("body", post.body)

        val objectRequest = JsonObjectRequest(Request.Method.POST, path, postJson,
            { response -> completionHandler() },
            { error -> completionHandler() })
        PostSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }
}