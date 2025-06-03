package com.example.posticks

data class Note(
    val id: Int,
    var title: String,
    var content: String,
    var creationDate: String,
    var isFavorite: Boolean = false
)
