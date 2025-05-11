package com.example.recipes.data

import com.example.recipes.R
import com.example.recipes.model.UserModel

object UserData {
    private val users = listOf(
        UserModel(
            name = R.string.user1_name,
            img = R.drawable.user,
            favourites = mutableListOf()
        )
    )

    fun getUsers(): List<UserModel> = users
}