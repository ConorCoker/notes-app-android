package com.example.notesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notesapp.controllers.NoteAPI
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import com.example.notesapp.models.Note
import com.example.notesapp.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    private var dateChanged = false
    private lateinit var completeBy: Date
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(context, "onCreateView() in create note has been called", Toast.LENGTH_LONG)
            .show()
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)
        setupCalender()
        setupOnClickListeners()
        return binding.root
    }

    private fun setupCalender() {

        val calendar = Calendar.getInstance()
        binding.calenderViewExpectedCompletion.minDate = calendar.timeInMillis
        calendar.add(Calendar.YEAR, 1)
        binding.calenderViewExpectedCompletion.maxDate = calendar.timeInMillis

    }

    private fun setupOnClickListeners() {

        binding.calenderViewExpectedCompletion.setOnDateChangeListener { _, year, month, day ->
            dateChanged = true
            Log.d("date", "year is $year month is $month day is $day")
            val formatter = SimpleDateFormat("dd-MM-yy", Locale.UK)

            val correctedMonth =
                month + 1 //for some reason month param is always a month behind selected month
            val date = formatter.parse("$day-$correctedMonth-$year")
            completeBy = date!!

        }

        binding.imageButtonCreateNote.setOnClickListener {
            if (allFieldsFilledAndDateChanged()) {
                if (NoteAPI.getInstance().addNote(
                        Note(
                            binding.editTextNoteTitle.text.toString(),
                            binding.editTextNoteContents.text.toString(),
                            completeBy
                        )
                    )
                ) {
                    Toast.makeText(context, "Note added!", Toast.LENGTH_LONG).show()
                    Utils.clearTextInputEditTexts(
                        binding.editTextNoteTitle,
                        binding.editTextNoteContents
                    )
                } else {
                    Toast.makeText(
                        context,
                        "Something went wrong while creating your note!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    context,
                    "You have not entered all required fields and/or changed date",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun allFieldsFilledAndDateChanged() =
        dateChanged && binding.editTextNoteTitle.text.toString()
            .isNotBlank() && binding.editTextNoteContents.text.toString().isNotBlank()


}