/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/11/2020
 */

package aragones.sergio.marvelpeople.models

import com.google.gson.annotations.SerializedName

data class UrlResponse(
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String
)