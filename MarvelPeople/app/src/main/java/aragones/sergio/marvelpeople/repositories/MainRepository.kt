/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.repositories

import aragones.sergio.marvelpeople.models.CharactersDataResponse
import aragones.sergio.marvelpeople.network.apiclient.CharacterAPIClient
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainRepository @Inject constructor(
        private val characterApiClient: CharacterAPIClient
) {

    //MARK: - Public methods

    fun getCharactersObserver(page: Int, search: String?): Single<CharactersDataResponse> {
        return characterApiClient.getCharactersObserver(page, search)
    }
}