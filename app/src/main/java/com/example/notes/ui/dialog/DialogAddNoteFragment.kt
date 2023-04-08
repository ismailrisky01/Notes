package com.example.notes.ui.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.notes.R
import com.example.notes.data.model.Note
import com.example.notes.databinding.FragmentDialogAddNoteBinding
import java.time.Instant
import java.time.format.DateTimeFormatter


class DialogAddNoteFragment(val addNote: (note: Note) -> Unit) : DialogFragment() {

    private var _binding: FragmentDialogAddNoteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDialogAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.add.setOnClickListener {
            val title = binding.title.text.toString()
            val desc = binding.desc.text.toString()
            val timestamp = System.currentTimeMillis()
            addNote.invoke(Note(title,desc,timestamp))
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
//            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }


}