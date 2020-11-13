package com.example.crudkotlinaso.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.crudkotlinaso.models.Post

class PostServiceImpl : IPostService {
    override fun getById(context: Context, postId: Int, completionHandler: (response: Post?) -> Unit) {
        val path = PostSingleton.getInstance(context).baseUrl + "/api/posts/" + postId
        val objectRequest = JsonObjectRequest(
            Request.Method.GET, path, null,
            { response ->
                if(response == null) completionHandler(null)

                val id = response.getInt("id")
                val title = response.getString("title")
                val body = response.getString("body")

                val post = Post(id,title,body,"","")
                completionHandler(post)
            },
            { error ->
                completionHandler(null)
            })
        PostSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }
}