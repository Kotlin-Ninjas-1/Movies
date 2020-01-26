package com.kotlininjas1.moviesninja1.network

import com.squareup.moshi.Json

data class Movie(
    @Json(name = "adult")
    val isForAdult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    @Json(name = "video")
    val hasVideo: Boolean,
    val vote_average: Double,
    val vote_count: Int
)