package com.example.myspurssubmission

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val nationality: String,
    val name: String,
    val position: String,
    val description: String,
    val photo: Int,
    val born: String,
    val heightWeight: String,
    val appearance:Int,
    val goals: Int,
    val wins: Int,

    ) : Parcelable {
}

