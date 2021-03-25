package ru.kpfu.itis.tmdb.data.api.response
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results : List<Results>
)

data class Results (
    @SerializedName("backdrop_path")
    val backdrop_path : String?,
    @SerializedName("id")
    val id : Int,
    @SerializedName("original_title")
    val original_title : String,
    @SerializedName("poster_path")
    val poster_path : String?,
    @SerializedName("title")
    val title : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("media_type")
    val type: String
)