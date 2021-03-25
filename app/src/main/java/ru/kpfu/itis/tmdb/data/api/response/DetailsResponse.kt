package ru.kpfu.itis.tmdb.data.api.response
import com.google.gson.annotations.SerializedName


data class DetailsResponse(
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("genres")
    var genres: List<Genre>,
    @SerializedName("id")
    var id: Int,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("popularity")
    var popularity: Double,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("revenue")
    var revenue: Int,
    @SerializedName("runtime")
    var runtime: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("first_air_date")
    var firstAirDate: String
)

data class Genre(
    @SerializedName("name")
    var name: String
)

