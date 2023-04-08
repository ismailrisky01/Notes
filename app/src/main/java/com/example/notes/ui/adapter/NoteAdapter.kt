package com.example.notes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.model.Note
import com.example.notes.databinding.ItemNoteBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var dataList = emptyList<Note>()
    fun setData(data: List<Note>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int =dataList.size

    class ViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}