package com.example.noteappjetpackcompose.domain.usecase

import com.example.noteappjetpackcompose.common.states.NoteUpdationState
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.domain.repository.NoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateNoteUsecase @Inject constructor(private val noteRepository: NoteRepository) {
    operator fun invoke(note: Note) =  flow {
        try {
            emit(NoteUpdationState.Loading)
            val response = noteRepository.updateNote(note)
            if (response == 0) {
                emit(NoteUpdationState.NoteUpdationFailed("No note was updated"))
            } else {
                emit(NoteUpdationState.NoteUpdated("$response note was updated"))
            }
        } catch (e: Exception) {
            emit(NoteUpdationState.NoteOperationFailed(e.localizedMessage ?: "Some error occured."))
        }
    }
}