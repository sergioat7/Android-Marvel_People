/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/11/2020
 */

package aragones.sergio.marvelpeople.models

import com.google.gson.annotations.SerializedName

data class ItemResponse(
        @SerializedName("resourceURI")
        val resourceURI: String,
        @SerializedName("name")
        val name: String
)