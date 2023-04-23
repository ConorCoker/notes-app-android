package com.example.notesapp.models

import android.content.Context
import android.net.Uri
import com.example.notesapp.helpers.exists
import com.example.notesapp.helpers.read
import com.example.notesapp.helpers.write
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "notes.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<Note>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class NoteJSONStore(private val context: Context) {

    private var notes = mutableListOf<Note>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    fun findAll(): MutableList<Note> {
        return notes
    }

    fun removeAll() {
        notes.clear()
        save()
    }

    fun getNoteById(id: Long) = notes.find { it.id == id }

    fun removeNoteById(id: Long) {
        notes.forEach {
            if (it.id == id)
                notes.remove(it)
            save()
        }
    }

    fun create(note: Note): Boolean {
        note.id = generateRandomId()
        val result = notes.add(note)
        serialize()
        return result
    }

    fun save() {
        serialize()
    }


    fun update(note: Note) {
        // todo
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(notes, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        notes = gsonBuilder.fromJson(jsonString, listType)
    }
}

class UriParser : JsonDeserializer<Uri>, JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}