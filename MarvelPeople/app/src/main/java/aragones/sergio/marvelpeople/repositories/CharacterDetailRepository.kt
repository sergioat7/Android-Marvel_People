/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 4/11/2020
 */

package aragones.sergio.marvelpeople.repositories

import aragones.sergio.marvelpeople.models.CharactersDataResponse
import aragones.sergio.marvelpeople.network.apiclient.CharacterAPIClient
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharacterDetailRepository @Inject constructor(
    private val characterApiClient: CharacterAPIClient
) {

    //MARK: - Public methods

    fun getCharacterObserver(characterId: Int): Single<CharactersDataResponse> {
        return characterApiClient.getCharacterObserver(characterId)
    }
}