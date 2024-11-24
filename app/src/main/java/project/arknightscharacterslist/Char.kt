package project.arknightscharacterslist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Char(
    val name: String,
    val descriptionProfile: String,
    val photoCharIcon: String,
    val photoClass: String,
    val photoSubclass: String,
    val descriptionStory: String,
    val descriptionOverview: String,
    val photoCharFaction: String,
    val photoCharElite1: String,
    val photoCharElite2: String,
) : Parcelable
