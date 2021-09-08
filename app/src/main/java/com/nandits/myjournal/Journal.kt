package com.nandits.myjournal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Journal(
    var title: String,
    var content: String,
    var date: String
): Parcelable
