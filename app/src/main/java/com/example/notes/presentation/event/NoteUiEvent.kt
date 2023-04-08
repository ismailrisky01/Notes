package com.example.notes.presentation.event

import com.example.notes.data.model.Note

sealed class NoteUiEvent {
    data class OnAddNote(val note: Note):NoteUiEvent()
}