package com.example.crudkotlinaso.service

import android.content.Context
import com.example.crudkotlinaso.models.Post

interface IPostService {
    fun getById(context: Context, postId: Int, completionHandler: (response: Post?) -> Unit)
    fun deletePost(context: Context, postId: Int, completionHandler: () -> Unit)
    fun updatePost(context: Context, post: Post, completionHandler: () -> Unit)
    //fun getAll(context: Context, completionHandler: (response: ArrayList<Post>?) -> Unit)

}