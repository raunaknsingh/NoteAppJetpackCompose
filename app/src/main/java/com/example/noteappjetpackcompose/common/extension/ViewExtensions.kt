package com.example.noteappjetpackcompose.common.extension

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String) {
    return Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}