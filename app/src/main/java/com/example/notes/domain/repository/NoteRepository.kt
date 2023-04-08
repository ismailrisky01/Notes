package com.example.notes.domain.repository

import com.example.notes.data.model.Note
import com.example.notes.util.Resource
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<Resource<List<Note>>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}