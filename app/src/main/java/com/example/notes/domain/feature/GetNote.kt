package com.example.notes.domain.feature

import com.example.notes.data.model.Note
import com.example.notes.domain.repository.NoteRepository
import com.example.notes.util.Resource
import kotlinx.coroutines.flow.Flow

class GetNote(private val noteRepository: NoteRepository) {
    operator fun invoke(): Flow<Resource<List<Note>>> {
        return noteRepository.getNotes()
    }
}