package com.example.notesapp.models

import java.time.LocalDate
import java.time.LocalDate.now

class Note(
    val title: String,
    val noteContents: String,
    var lastsUntil: LocalDate,
) : java.io.Serializable {
    val createdDate: LocalDate? = now()
}