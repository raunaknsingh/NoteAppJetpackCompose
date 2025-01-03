package com.example.noteappjetpackcompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val NOTE_DATABASE = "note_db"

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context) =
            Room.databaseBuilder(context, NoteDatabase::class.java, NOTE_DATABASE).build()
    }

    abstract fun noteDao(): NoteDao
}