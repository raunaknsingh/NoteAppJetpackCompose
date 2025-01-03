package com.example.noteappjetpackcompose.common.destinations

import com.example.noteappjetpackcompose.domain.model.Note
import kotlinx.serialization.Serializable

sealed class Destination {

    @Serializable
    data object NoteListingScreen: Destination()

    @Serializable
    data class NoteAddEditScreen(val note: Note): Destination()
}