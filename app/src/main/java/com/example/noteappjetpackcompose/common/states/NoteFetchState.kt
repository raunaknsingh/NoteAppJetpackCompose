package com.example.noteappjetpackcompose.common.states

import com.example.noteappjetpackcompose.domain.model.Note

sealed class NoteFetchState {

    data object Loading : NoteFetchState()

    data class AllNotesFetched(val notes: List<Note>) : NoteFetchState()
    data class AllNotesFetchFailed(val error: String) : NoteFetchState()

    data class NoteOperationFailed(val error: String) : NoteFetchState()
}