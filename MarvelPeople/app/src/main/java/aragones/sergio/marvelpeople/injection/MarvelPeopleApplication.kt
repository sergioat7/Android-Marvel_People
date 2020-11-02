/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.injection

import android.app.Application

class MarvelPeopleApplication: Application() {

    lateinit var characterApiClientComponent: CharacterAPIClientComponent

    override fun onCreate() {
        super.onCreate()

        characterApiClientComponent = DaggerCharacterAPIClientComponent.builder().characterAPIClientModule(
            CharacterAPIClientModule()
        ).build()
    }
}