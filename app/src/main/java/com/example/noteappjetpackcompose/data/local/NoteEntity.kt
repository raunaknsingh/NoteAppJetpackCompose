package com.example.noteappjetpackcompose.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteappjetpackcompose.domain.model.Note


@Entity("note_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val noteId: Int,
    @ColumnInfo("title") val noteTitle: String,
    @ColumnInfo("subtitle") val noteSubTitle: String,
) {
    constructor() : this(0, "", "")
}

fun NoteEntity.toNote(): Note {
    return Note(
        noteId = this.noteId,
        noteTitle = this.noteTitle,
        noteSubTitle = this.noteSubTitle
    )
}