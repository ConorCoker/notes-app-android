package com.example.notesapp.models

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate.now
import java.time.format.DateTimeFormatter
import java.util.*

class Note(
    val title: String,
    val noteContents: String,
    var lastsUntil: String,
) : java.io.Serializable {
    private val formatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(
            Locale.ENGLISH
        )
    val createdDate: String = now().format(formatter)
    var id:Long = 0L
    @SuppressLint("SimpleDateFormat")
    fun isExpired():Boolean{
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        try {
            val date:Date? = formatter.parse(lastsUntil)
            return !date!!.after(Date())
        }catch (_:ParseException){

        }
        return false
    }
}

