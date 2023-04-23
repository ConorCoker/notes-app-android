package com.example.notesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteJSONStore
import com.example.notesapp.utils.Utils
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    private var dateChanged = false
    private var completeBy = LocalDate.now()
    private lateinit var notes: NoteJSONStore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)
        setupCalender()
        setupOnClickListeners()
        notes = NoteJSONStore(requireContext())
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
            completeBy = LocalDate.of(year, month + 1, day)
            Log.d("date","completeBy is set to: $completeBy")
        }


        binding.imageButtonCreateNote.setOnClickListener {
            if (allFieldsFilledAndDateChanged()) {
                val formatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(
                        Locale.ENGLISH
                    )
                if (notes.create(
                        Note(
                            binding.editTextNoteTitle.text.toString(),
                            binding.editTextNoteContents.text.toString(),
                            completeBy.format(formatter)
                        )
                    )
                ) {

                    notes.save()
                    Utils.clearTextInputEditTexts(
                        binding.editTextNoteTitle,
                        binding.editTextNoteContents
                    )

                } else Toast.makeText(
                    context,
                    "Something went wrong whilst creating your note!",
                    Toast.LENGTH_LONG
                ).show()
            } else Toast.makeText(
                context,
                "You have not entered all required fields and/or changed date!",
                Toast.LENGTH_LONG
            ).show()

        }
    }

    private fun allFieldsFilledAndDateChanged() =
        dateChanged && binding.editTextNoteTitle.text.toString()
            .isNotBlank() && binding.editTextNoteContents.text.toString().isNotBlank()


}
