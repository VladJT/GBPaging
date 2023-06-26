package jt.projects.gbpaging.repository.dto

data class Data(
    val after: String,
    val before: Any,
    val children: List<Children>,
    val dist: Int,
    val geo_filter: Any,
    val modhash: String
)