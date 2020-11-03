/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.repositories

import aragones.sergio.marvelpeople.network.apiclient.CharacterAPIClient
import javax.inject.Inject

class MainRepository @Inject constructor(
        private val characterApiClient: CharacterAPIClient
) {
    // TODO: Implement the Repository
}