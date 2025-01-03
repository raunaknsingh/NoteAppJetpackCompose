package com.example.noteappjetpackcompose.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteappjetpackcompose.R
import com.example.noteappjetpackcompose.domain.model.Note

@Preview
@Composable
fun NoteListItemPreview(modifier: Modifier = Modifier) {
    NoteListItem(Note(1, "Note Title", "Note Subtitle"), {}, {})
}


@Composable
fun NoteListItem(note: Note, onNoteClick: (Note) -> Unit, onDeleteNoteClick: (Note) -> Unit) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                onNoteClick(note)
            },
        shape = RoundedCornerShape(12.dp),
        color = Color.Cyan
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            Column(modifier = Modifier.weight(0.9f)) {
                Text(note.noteTitle)
                Spacer(Modifier.height(20.dp))
                Text(note.noteSubTitle)
            }

            IconButton(
                onClick = {
                    onDeleteNoteClick(note)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_delete_24),
                    contentDescription = null,
                    modifier = Modifier.weight(0.1f)
                )
            }

        }
    }
}