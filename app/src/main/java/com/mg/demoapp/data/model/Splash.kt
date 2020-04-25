package com.mg.demoapp.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class Splash(
    val id: Int,
    val avg_time: Int,
    val currency: String,
    @SerializedName("is_has_data") val isHasData: Boolean
):Error()
