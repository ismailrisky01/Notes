package com.example.notes.domain.feature

import com.example.notes.data.model.Note
import com.example.notes.domain.repository.NoteRepository
import com.example.notes.util.Resource
import java.util.concurrent.Flow

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) {
        noteRepository.insertNote(note)
    }
}