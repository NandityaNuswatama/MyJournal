package com.nandits.myjournal

import android.app.Activity
import android.widget.Toast
import java.text.SimpleDateFormat

const val SP_NAME = "My Shared Preference"
const val PIN = "App Pin"

fun milisToDate(input: Long): String {
    val format = SimpleDateFormat("dd MMMM yyyy")
    return format.format(input)
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}