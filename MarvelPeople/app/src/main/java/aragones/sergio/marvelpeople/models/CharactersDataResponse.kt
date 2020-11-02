/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/11/2020
 */

package aragones.sergio.marvelpeople.models

import com.google.gson.annotations.SerializedName

data class CharactersDataResponse(
        @SerializedName("code")
        val code: Int,
        @SerializedName("status")
        val status: String,
        @SerializedName("copyright")
        val copyright: String,
        @SerializedName("attributionText")
        val attributionText: String,
        @SerializedName("attributionHTML")
        val attributionHTML: String,
        @SerializedName("etag")
        val etag: String,
        @SerializedName("data")
        val data: CharacterListResponse
)