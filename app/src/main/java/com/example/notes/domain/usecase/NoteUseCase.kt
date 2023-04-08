package com.example.notes.domain.usecase

import com.example.notes.domain.feature.AddNote
import com.example.notes.domain.feature.DeleteNote
import com.example.notes.domain.feature.GetNote
import com.example.notes.domain.feature.UpdateNote

data class NoteUseCase (val getNote: GetNote, val addNote: AddNote,val deleteNote: DeleteNote,val updateNote: UpdateNote)