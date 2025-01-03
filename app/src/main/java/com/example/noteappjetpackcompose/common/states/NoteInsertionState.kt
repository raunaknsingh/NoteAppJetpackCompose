package com.example.noteappjetpackcompose.common.states

sealed class NoteInsertionState {

    data object Loading : NoteInsertionState()

    data class NoteInserted(val noteRecordId: String) : NoteInsertionState()
    data class NoteInsertionFailed(val error: String) : NoteInsertionState()

    data class NoteOperationFailed(val error: String) : NoteInsertionState()
}