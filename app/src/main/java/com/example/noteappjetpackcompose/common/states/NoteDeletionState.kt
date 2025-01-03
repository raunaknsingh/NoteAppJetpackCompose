package com.example.noteappjetpackcompose.common.states

sealed class NoteDeletionState {

    data object Loading : NoteDeletionState()

    data class NoteDeleted(val noOfRowsUpdated: String) : NoteDeletionState()
    data class NoteDeletionFailed(val error: String) : NoteDeletionState()

    data class NoteOperationFailed(val error: String) : NoteDeletionState()
}