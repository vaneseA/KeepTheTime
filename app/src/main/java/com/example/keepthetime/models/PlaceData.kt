package com.example.keepthetime.models

import com.google.gson.annotations.SerializedName

class PlaceData(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longtude: Double,
    @SerializedName("is_primary")
    val isPrimary: Boolean,
) {
}