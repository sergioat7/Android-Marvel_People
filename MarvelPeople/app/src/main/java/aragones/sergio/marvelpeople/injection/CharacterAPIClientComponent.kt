/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.injection

import aragones.sergio.marvelpeople.viewmodelfactories.CharacterDetailViewModelFactory
import aragones.sergio.marvelpeople.viewmodelfactories.MainViewModelFactory
import dagger.Component

@Component(modules = [CharacterAPIClientModule::class])
interface CharacterAPIClientComponent {

    fun inject(mainViewModelFactory: MainViewModelFactory)
    fun inject(characterDetailViewModelFactory: CharacterDetailViewModelFactory)
}