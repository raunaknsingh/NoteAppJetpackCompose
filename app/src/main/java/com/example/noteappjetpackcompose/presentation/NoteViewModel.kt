package com.example.noteappjetpackcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappjetpackcompose.common.states.NoteDeletionState
import com.example.noteappjetpackcompose.common.states.NoteFetchState
import com.example.noteappjetpackcompose.common.states.NoteInsertionState
import com.example.noteappjetpackcompose.common.states.NoteUpdationState
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.domain.usecase.DeleteNoteUsecase
import com.example.noteappjetpackcompose.domain.usecase.GetAllNotesUsecase
import com.example.noteappjetpackcompose.domain.usecase.InsertNoteUsecase
import com.example.noteappjetpackcompose.domain.usecase.UpdateNoteUsecase
import com.example.noteappjetpackcompose.presentation.uistate.NoteListingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val insertNoteUsecase: InsertNoteUsecase,
    private val updateNoteUsecase: UpdateNoteUsecase,
    private val deleteNoteUsecase: DeleteNoteUsecase,
    private val getAllNotesUsecase: GetAllNotesUsecase
) : ViewModel() {

    private var _noteListingUiState = MutableStateFlow(NoteListingUiState())
    val noteListingUiState: StateFlow<NoteListingUiState> = _noteListingUiState.asStateFlow()

    init {
        getAllNotes()
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            insertNoteUsecase(note).collect {
                when (it) {
                    is NoteInsertionState.Loading -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = true,
                                isOperationSuccessful = false
                            )
                        }
                    }

                    is NoteInsertionState.NoteInsertionFailed -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = false,
                                isOperationSuccessful = false,
                                errorMessage = it.error
                            )
                        }
                    }

                    is NoteInsertionState.NoteOperationFailed -> {
                        _noteListingUiState.update { uiState ->
                            NoteListingUiState(
                                isLoading = false,
                                isOperationSuccessful = false,
                                errorMessage = it.error
                            )
                        }
                    }

                    is NoteInsertionState.NoteInserted -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = false,
                                isOperationSuccessful = true,
                                successMessage = it.noteRecordId
                            )
                        }
                    }
                }
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            updateNoteUsecase(note).collect {
                when (it) {
                    is NoteUpdationState.Loading -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = true,
                                isOperationSuccessful = false
                            )
                        }
                    }

                    is NoteUpdationState.NoteUpdationFailed -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = false,
                                isOperationSuccessful = false,
                                errorMessage = it.error
                            )
                        }
                    }

                    is NoteUpdationState.NoteOperationFailed -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = false,
                                isOperationSuccessful = false,
                                errorMessage = it.error
                            )
                        }
                    }

                    is NoteUpdationState.NoteUpdated -> {
                        _noteListingUiState.update { uiState ->
                            NoteListingUiState(
                                isLoading = false,
                                isOperationSuccessful = true,
                                successMessage = it.noOfRowsUpdated
                            )
                        }
                    }
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUsecase(note).collect {
                when (it) {
                    is NoteDeletionState.Loading -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = true,
                                isOperationSuccessful = false
                            )
                        }
                    }

                    is NoteDeletionState.NoteDeletionFailed -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = true,
                                isOperationSuccessful = false,
                                errorMessage = it.error
                            )
                        }
                    }

                    is NoteDeletionState.NoteOperationFailed -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = true,
                                isOperationSuccessful = false,
                                errorMessage = it.error
                            )
                        }
                    }

                    is NoteDeletionState.NoteDeleted -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = false,
                                isOperationSuccessful = true,
                                successMessage = it.noOfRowsUpdated
                            )
                        }
                    }
                }
            }
        }
    }

    fun getAllNotes() {
        viewModelScope.launch {
            getAllNotesUsecase().collect {
                when (it) {
                    is NoteFetchState.Loading -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = true
                            )
                        }
                    }

                    is NoteFetchState.AllNotesFetchFailed -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = false,
                                errorMessage = it.error
                            )
                        }
                    }

                    is NoteFetchState.NoteOperationFailed -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = false,
                                errorMessage = it.error
                            )
                        }
                    }

                    is NoteFetchState.AllNotesFetched -> {
                        _noteListingUiState.update { uiState ->
                            uiState.copy(
                                isLoading = false,
                                noteList = it.notes
                            )
                        }
                    }
                }
            }
        }
    }
}