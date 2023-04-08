package com.example.notes.data.repository

import com.example.notes.data.local.NoteDao
import com.example.notes.data.model.Note
import com.example.notes.domain.repository.NoteRepository
import com.example.notes.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<Resource<List<Note>>> {
        return flow {
            emit(Resource.loading(null))
            delay(1000)
            emit(Resource.success(dao.getNotes()))
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        dao.insertNote(note)

    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}