/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.injection

import aragones.sergio.marvelpeople.network.apiclient.CharacterAPIClient
import dagger.Module
import dagger.Provides

@Module
class CharacterAPIClientModule () {

    @Provides
    fun provideCharacterAPIClient(): CharacterAPIClient = CharacterAPIClient()
}