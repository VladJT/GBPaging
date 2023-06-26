package jt.projects.gbpaging.repository.dto

import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("data")
    val d: DataX,

    @SerializedName("kind")
    val kind: String
)