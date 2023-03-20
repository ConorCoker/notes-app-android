package com.example.notesapp.utils

import com.google.android.material.textfield.TextInputEditText

object Utils {

    @JvmStatic
    fun isValidIndex(index: Int, list: List<Any>): Boolean {
        return index >= 0 && index < list.size
    }

    @JvmStatic
    fun clearTextInputEditTexts(vararg field:TextInputEditText){
        field.forEach {
            it.setText("")
        }
    }
}