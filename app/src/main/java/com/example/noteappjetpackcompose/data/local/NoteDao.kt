package com.example.noteappjetpackcompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Update
    suspend fun updateNote(noteEntity: NoteEntity): Int

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity): Int

    @Query("Select * FROM note_table")
    suspend fun getAllNotes(): List<NoteEntity>
}