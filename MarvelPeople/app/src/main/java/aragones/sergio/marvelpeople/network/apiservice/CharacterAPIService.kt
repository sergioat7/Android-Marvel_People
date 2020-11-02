/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/11/2020
 */

package aragones.sergio.marvelpeople.network.apiservice

import aragones.sergio.marvelpeople.models.CharactersDataResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface CharacterAPIService {

    @GET("characters")
    fun getCharacters(@QueryMap queryParams: Map<String, String>): Single<CharactersDataResponse>

    @GET("characters/{characterId}")
    fun getCharacter(@Path(value = "characterId") characterId: Int, @QueryMap queryParams: Map<String, String>): Single<CharactersDataResponse>
}