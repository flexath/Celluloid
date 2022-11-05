package com.flexath.celluloid.data.retrofit.credits

import java.io.Serializable

data class Credits(
    val cast: List<com.flexath.celluloid.data.retrofit.credits.Cast>,
    val crew: List<com.flexath.celluloid.data.retrofit.credits.Crew>,
    val id: Int
):Serializable