package com.example.noteappjetpackcompose.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.noteappjetpackcompose.common.destinations.Destination
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.screens.NoteAddEditScreen
import com.example.noteappjetpackcompose.presentation.screens.NoteListingScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf


val NoteType = object : NavType<Note>(false) {
    override fun get(bundle: Bundle, key: String): Note? {
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Note::class.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): Note {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: Note) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: Note): String {
        return Json.encodeToString(value)
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.NoteListingScreen) {

        composable<Destination.NoteListingScreen> {
            NoteListingScreen(onNoteClick = {
                navController.navigate(Destination.NoteAddEditScreen(it))
            }, onCreateNote = {
                navController.navigate(Destination.NoteAddEditScreen(Note(0, "", "")))
            })
        }

        composable<Destination.NoteAddEditScreen>(
            typeMap = mapOf(typeOf<Note>() to NoteType)
        ) {
            val note = it.toRoute<Destination.NoteAddEditScreen>()
            Log.d("Navigation", "Note: $note")
            NoteAddEditScreen(note){
                navController.popBackStack()
            }
        }
    }
}