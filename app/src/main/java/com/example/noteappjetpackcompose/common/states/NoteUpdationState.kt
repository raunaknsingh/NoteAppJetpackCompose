package com.example.noteappjetpackcompose.common.states

sealed class NoteUpdationState {

    data object Loading : NoteUpdationState()

    data class NoteUpdated(val noOfRowsUpdated: String) : NoteUpdationState()
    data class NoteUpdationFailed(val error: String) : NoteUpdationState()

    data class NoteOperationFailed(val error: String) : NoteUpdationState()
}