/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/11/2020
 */

package aragones.sergio.marvelpeople.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("modified")
        val modified: String,
        @SerializedName("thumbnail")
        val thumbnail: ThumbnailResponse,
        @SerializedName("resourceURI")
        val resourceURI: String,
        @SerializedName("comics")
        val comics: ExtraDataListResponse,
        @SerializedName("series")
        val series: ExtraDataListResponse,
        @SerializedName("stories")
        val stories: ExtraDataListResponse,
        @SerializedName("events")
        val events: ExtraDataListResponse,
        @SerializedName("urls")
        val urls: List<UrlResponse>
)