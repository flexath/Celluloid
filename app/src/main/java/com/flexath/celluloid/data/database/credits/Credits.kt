package com.flexath.celluloid.data.database.credits

import java.io.Serializable

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
):Serializable