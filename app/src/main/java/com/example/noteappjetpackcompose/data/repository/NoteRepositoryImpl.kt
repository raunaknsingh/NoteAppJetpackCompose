package com.example.noteappjetpackcompose.data.repository

import com.example.noteappjetpackcompose.data.local.NoteDao
import com.example.noteappjetpackcompose.data.local.toNote
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.domain.model.toNoteEntity
import com.example.noteappjetpackcompose.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun insertNote(note: Note) = noteDao.insertNote(note.toNoteEntity())

    override suspend fun updateNote(note: Note) = noteDao.updateNote(note.toNoteEntity())

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note.toNoteEntity())

    override suspend fun getAllNotes() = noteDao.getAllNotes().map { it.toNote() }
}