package com.example.notes.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.di.viewModel
import com.example.notes.domain.usecase.NoteUseCase
import com.example.notes.presentation.event.NoteUiEvent
import com.example.notes.presentation.state.NoteUiState
import com.example.notes.util.Resource
import com.example.notes.util.Status
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class NoteViewModel(private val noteUseCase: NoteUseCase) : ViewModel() {
    private val _state = Channel<NoteUiState>()
    val state = _state.receiveAsFlow()

    init {
        getNote()
    }

    fun onEvent(event: NoteUiEvent) {
        when (event) {
            is NoteUiEvent.OnAddNote -> {
                viewModelScope.launch {
                    noteUseCase.addNote.invoke(event.note)
                    getNote()
                }
            }
        }

    }

    fun getNote() {
        Log.d("Ismail Log", "Get Note")

        viewModelScope.launch {

            noteUseCase.getNote.invoke().collect { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        Log.d("Ismail Log", result.data.toString())
                        sendUiState(NoteUiState.OnShowNote(result.data!!))
                    }
                    Status.ERROR -> {
                        Log.d("Ismail Log", result.message.toString())


                    }
                    Status.LOADING -> {

                        Log.d("Ismail Log", "LOADING")

                    }
                }
            }

        }

    }

    private fun sendUiState(state: NoteUiState) {
        viewModelScope.launch {
            _state.send(state)
        }
    }
}