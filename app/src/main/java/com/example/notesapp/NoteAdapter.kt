package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.models.Note
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class NoteAdapter(private val mList: ArrayList<Note>, private val listener: (Note) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textViewContents.text = itemsViewModel.noteContents

        holder.textViewTitle.text = itemsViewModel.title

        holder.itemView.setOnClickListener { listener(itemsViewModel) }

        holder.textViewCreatedAt.text = itemsViewModel.createdDate

        holder.textViewLastsUntil.text = itemsViewModel.lastsUntil

//        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(
//            Locale.ENGLISH
//        )
//        val formattedCreatedDate = itemsViewModel.createdDate!!.format(formatter)
//
//        holder.textViewCreatedAt.text = formattedCreatedDate
//
//        val formattedLastUntil = itemsViewModel.lastsUntil.format(formatter)
//
//        holder.textViewLastsUntil.text = formattedLastUntil
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewContents: TextView = itemView.findViewById(R.id.text_view_contents)
        val textViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        val textViewCreatedAt: TextView =
            itemView.findViewById(R.id.text_view_date_created_contents)
        val textViewLastsUntil: TextView =
            itemView.findViewById(R.id.text_view_lasts_until_contents)
    }
}