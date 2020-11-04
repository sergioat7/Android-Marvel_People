/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/11/2020
 */

package aragones.sergio.marvelpeople.models

import com.google.gson.annotations.SerializedName

data class ExtraDataListResponse(
        @SerializedName("available")
        val available: Int,
        @SerializedName("collectionURI")
        val collectionURI: String,
        @SerializedName("items")
        val items: List<ItemResponse>,
        @SerializedName("returned")
        val returned: Int
)