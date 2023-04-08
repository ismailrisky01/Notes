package com.example.notes.domain.feature

import com.example.notes.data.model.Note
import com.example.notes.domain.repository.NoteRepository

class DeleteNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note)
    }
}