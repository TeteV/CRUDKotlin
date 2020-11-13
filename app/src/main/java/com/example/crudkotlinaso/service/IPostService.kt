package com.example.crudkotlinaso.service

import android.content.Context
import com.example.crudkotlinaso.models.Post

interface IPostService {
    fun getById(context: Context, postId: Int, completionHandler: (response: Post?) -> Unit)

}