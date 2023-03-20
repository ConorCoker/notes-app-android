package com.example.notesapp.models

import java.util.Date

class Note(
    val title: String,
    val noteContents: String,
    var lastsUntil: Date,
) {
    val createdDate = Date()
}