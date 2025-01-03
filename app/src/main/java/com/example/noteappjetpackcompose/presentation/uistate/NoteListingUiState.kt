package com.example.noteappjetpackcompose.presentation.uistate

import com.example.noteappjetpackcompose.domain.model.Note

data class NoteListingUiState(
    val isLoading: Boolean = false,
    val noteList: List<Note> = emptyList(),
    val errorMessage: String = "",
    val isOperationSuccessful: Boolean = false,
    val successMessage: String = ""
)