package com.example.projectofinalteamjr.api

import java.io.Serializable

data class User(
    val name : String,
    val morada : String,
    val email : String,
    val nib : String,
    val telefone : Int,
):Serializable
