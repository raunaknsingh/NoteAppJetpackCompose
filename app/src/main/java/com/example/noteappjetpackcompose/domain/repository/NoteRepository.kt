package com.example.noteappjetpackcompose.domain.repository

import com.example.noteappjetpackcompose.domain.model.Note

interface NoteRepository {

    suspend fun insertNote(note: Note): Long

    suspend fun updateNote(note: Note): Int

    suspend fun deleteNote(note: Note): Int

    suspend fun getAllNotes(): List<Note>
}