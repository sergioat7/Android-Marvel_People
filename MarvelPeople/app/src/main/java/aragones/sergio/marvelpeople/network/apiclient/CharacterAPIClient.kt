/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/11/2020
 */

package aragones.sergio.marvelpeople.network.apiclient

import aragones.sergio.marvelpeople.models.CharactersDataResponse
import aragones.sergio.marvelpeople.network.apiservice.CharacterAPIService
import aragones.sergio.marvelpeople.utils.Constants
import io.reactivex.rxjava3.core.Single

class CharacterAPIClient {

    private val api = APIClient.retrofit.create(CharacterAPIService::class.java)

    fun getCharactersObserver(page: Int, search: String?): Single<CharactersDataResponse> {

        val queryParams: MutableMap<String, String> = Constants.getDefaultQueryParams()
        queryParams[Constants.LIMIT_PARAM] = Constants.LIMIT.toString()
        queryParams[Constants.OFFSET_PARAM] = ((page - 1) * Constants.LIMIT).toString()
        if (!search.isNullOrBlank()) {
            queryParams[Constants.NAME_STARTS_WITH_PARAM] = search
        }

        return api
                .getCharacters(queryParams)
                .subscribeOn(Constants.SUBSCRIBER_SCHEDULER)
                .observeOn(Constants.OBSERVER_SCHEDULER)
    }

    fun getCharacterObserver(characterId: Int): Single<CharactersDataResponse> {

        val queryParams: MutableMap<String, String> = Constants.getDefaultQueryParams()

        return api
                .getCharacter(characterId, queryParams)
                .subscribeOn(Constants.SUBSCRIBER_SCHEDULER)
                .observeOn(Constants.OBSERVER_SCHEDULER)
    }
}