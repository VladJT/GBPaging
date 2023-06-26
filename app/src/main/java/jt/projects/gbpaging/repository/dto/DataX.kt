package jt.projects.gbpaging.repository.dto

import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("num_comments")
    val num_comments: Int,
    @SerializedName("score")
    val score: Int,
    @SerializedName("title")
    val title: String
)