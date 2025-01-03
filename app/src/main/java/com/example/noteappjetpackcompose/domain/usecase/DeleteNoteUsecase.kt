package com.example.noteappjetpackcompose.domain.usecase

import com.example.noteappjetpackcompose.common.states.NoteDeletionState
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.domain.repository.NoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteNoteUsecase @Inject constructor(private val noteRepository: NoteRepository) {
    operator fun invoke(note: Note) =  flow {
        try {
            emit(NoteDeletionState.Loading)
            val response = noteRepository.deleteNote(note)
            if (response == 0) {
                emit(NoteDeletionState.NoteDeletionFailed("No note deleted"))
            } else {
                emit(NoteDeletionState.NoteDeleted("$response note was deleted"))
            }
        } catch (e: Exception) {
            emit(NoteDeletionState.NoteOperationFailed(e.localizedMessage ?: "Some error occured."))
        }
    }
}