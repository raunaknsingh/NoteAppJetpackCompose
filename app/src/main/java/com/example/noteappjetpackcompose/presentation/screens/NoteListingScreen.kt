package com.example.noteappjetpackcompose.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteappjetpackcompose.R
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.NoteViewModel
import com.example.noteappjetpackcompose.presentation.components.NoteListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListingScreen(onNoteClick: (Note) -> Unit, onCreateNote: () -> Unit) {

    val noteViewModel: NoteViewModel = hiltViewModel()
    val noteListingUiState = noteViewModel.noteListingUiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Note listing screen")
        }, modifier = Modifier.fillMaxWidth())
    }, floatingActionButton = {
        FloatingActionButton(
            content = {
                Icon(painter = painterResource(R.drawable.baseline_add_24), contentDescription = null)
            },
            onClick = {
                onCreateNote()
            }
        )
    }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            if (noteListingUiState.value.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (noteListingUiState.value.errorMessage.isNotBlank()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(noteListingUiState.value.errorMessage)
                }
            } else if (noteListingUiState.value.isOperationSuccessful) {
                noteViewModel.getAllNotes()
            } else if (noteListingUiState.value.noteList.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(noteListingUiState.value.noteList.size) {
                        NoteListItem(noteListingUiState.value.noteList[it], onNoteClick) { note ->
                            noteViewModel.deleteNote(note)
                        }
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Click \'+\' button to add notes")
                }
            }
        }
    }
}
