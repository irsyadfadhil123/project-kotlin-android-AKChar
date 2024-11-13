package project.arknightscharacterslist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Char(
    val name: String,
    val description: String,
    val photoChar: String,
    val photoClass: String,
    val photoSubclass: String
) : Parcelable
