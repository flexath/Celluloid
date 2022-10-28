package com.flexath.celluloid.data.database.credits

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)