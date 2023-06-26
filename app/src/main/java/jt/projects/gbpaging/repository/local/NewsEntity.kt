package jt.projects.gbpaging.repository.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @field:PrimaryKey(autoGenerate = true)
    @field: ColumnInfo(name = "id")
    var id: Int = 0,

    @field: ColumnInfo(name = "title")
    val title: String,

    @field: ColumnInfo(name = "stars")
    val stars: Int,

    @field: ColumnInfo(name = "comments")
    val comments: Int
)
