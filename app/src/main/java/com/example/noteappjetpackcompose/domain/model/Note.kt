package com.example.noteappjetpackcompose.domain.model

import android.os.Parcelable
import com.example.noteappjetpackcompose.data.local.NoteEntity
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Note(
    val noteId: Int,
    val noteTitle: String,
    val noteSubTitle: String,
): Parcelable

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        noteId = this.noteId,
        noteTitle = this.noteTitle,
        noteSubTitle = this.noteSubTitle
    )
}
