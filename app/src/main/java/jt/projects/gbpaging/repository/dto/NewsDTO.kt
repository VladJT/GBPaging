package jt.projects.gbpaging.repository.dto

import com.google.gson.annotations.SerializedName

data class NewsDTO(
    @SerializedName("data")
    val d: Data
)