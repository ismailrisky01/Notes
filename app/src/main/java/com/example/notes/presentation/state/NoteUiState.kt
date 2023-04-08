package com.example.notes.presentation.state

import com.example.notes.data.model.Note

sealed class NoteUiState {
    val note: List<Note> = emptyList()
    data class OnShowNote(val data:List<Note>):NoteUiState()
}