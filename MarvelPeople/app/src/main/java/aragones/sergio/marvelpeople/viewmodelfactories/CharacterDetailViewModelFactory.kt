/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 4/11/2020
 */

package aragones.sergio.marvelpeople.viewmodelfactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import aragones.sergio.marvelpeople.injection.MarvelPeopleApplication
import aragones.sergio.marvelpeople.network.apiclient.CharacterAPIClient
import aragones.sergio.marvelpeople.repositories.CharacterDetailRepository
import aragones.sergio.marvelpeople.viewmodels.CharacterDetailViewModel
import javax.inject.Inject

class CharacterDetailViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {

    //MARK: - Public properties

    @Inject
    lateinit var characterApiClient: CharacterAPIClient
    @Inject
    lateinit var characterDetailRepository: CharacterDetailRepository
    @Inject
    lateinit var characterDetailViewModel: CharacterDetailViewModel

    //MARK: - Lifecycle methods

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)) {

            (application as MarvelPeopleApplication).characterApiClientComponent.inject(this)
            return characterDetailViewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}