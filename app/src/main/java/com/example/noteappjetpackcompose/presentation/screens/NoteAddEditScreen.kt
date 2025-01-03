package com.example.noteappjetpackcompose.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteappjetpackcompose.common.destinations.Destination
import com.example.noteappjetpackcompose.common.extension.showToast
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.presentation.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAddEditScreen(
    noteObject: Destination.NoteAddEditScreen,
    onBackButtonClick: () -> Unit
) {

    BackHandler {
        onBackButtonClick()
    }

    val noteViewModel: NoteViewModel = hiltViewModel()
    val noteListingUiState = noteViewModel.noteListingUiState.collectAsState()

    val title = remember { mutableStateOf(noteObject.note.noteTitle) }
    val description = remember { mutableStateOf(noteObject.note.noteSubTitle) }

    if (noteListingUiState.value.isOperationSuccessful) {
//        LocalContext.current.showToast(noteListingUiState.value.successMessage)
        onBackButtonClick()
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Note detail screen")
        }, modifier = Modifier.fillMaxWidth())
    }) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title.value,
                    label = {
                        Text("Note title")
                    },
                    maxLines = 2,
                    onValueChange = {
                        title.value = it
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = description.value,
                    label = {
                        Text("Note description")
                    },
                    maxLines = 10,
                    onValueChange = {
                        description.value = it
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (noteObject.note.noteId != 0) {
                            val note = Note(
                                noteId = noteObject.note.noteId,
                                noteTitle = title.value,
                                noteSubTitle = description.value
                            )
                            noteViewModel.updateNote(note)
                        } else {
                            val note = Note(
                                noteId = 0,
                                noteTitle = title.value,
                                noteSubTitle = description.value
                            )
                            noteViewModel.insertNote(note)
                        }

                    }
                ) {
                    if (noteObject.note.noteId != 0) {
                        Text("Update Note")
                    } else {
                        Text("Save Note")
                    }
                }
            }
        }
    }
}