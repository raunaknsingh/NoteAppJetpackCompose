package com.example.noteappjetpackcompose.domain.usecase

import com.example.noteappjetpackcompose.common.states.NoteInsertionState
import com.example.noteappjetpackcompose.domain.model.Note
import com.example.noteappjetpackcompose.domain.repository.NoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertNoteUsecase @Inject constructor(private val noteRepository: NoteRepository) {
    operator fun invoke(note: Note) =  flow {
        try {
            emit(NoteInsertionState.Loading)
            val response = noteRepository.insertNote(note)
            if (response == (-1).toLong()) {
                emit(NoteInsertionState.NoteInsertionFailed("No record inserted"))
            } else {
                emit(NoteInsertionState.NoteInserted("Inserted record id is $response"))
            }
        } catch (e: Exception) {
            emit(NoteInsertionState.NoteOperationFailed(e.localizedMessage ?: "Some error occured."))
        }
    }
}