package com.example.ivan.recyclerviewwithmultipletypes

import androidx.annotation.ColorInt


sealed class DataModel {
    data class Header(
        @ColorInt val bgColor: Int,
        val title: String
    ) : DataModel()

    data class Family(
        val name: String,
        val relationship: String
    ) : DataModel()

    data class Friend(
        val name: String,
        val gender: String
    ) : DataModel()

    data class Colleague(
        val name: String,
        val organization: String,
        val designation: String
    ) : DataModel()
}
