package com.mg.demoapp.data.model

import com.google.gson.annotations.SerializedName

data class ApiResult<T>(@SerializedName("status") val status: String,
                        @SerializedName("data") val data: T)