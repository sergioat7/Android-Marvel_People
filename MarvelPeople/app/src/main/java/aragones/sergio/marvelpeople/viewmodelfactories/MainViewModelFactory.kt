/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.viewmodelfactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import aragones.sergio.marvelpeople.injection.MarvelPeopleApplication
import aragones.sergio.marvelpeople.network.apiclient.CharacterAPIClient
import aragones.sergio.marvelpeople.repositories.MainRepository
import aragones.sergio.marvelpeople.viewmodels.MainViewModel
import javax.inject.Inject

class MainViewModelFactory(
        private val application: Application
): ViewModelProvider.Factory {

    //MARK: - Public properties

    @Inject
    lateinit var characterApiClient: CharacterAPIClient
    @Inject
    lateinit var mainRepository: MainRepository
    @Inject
    lateinit var mainViewModel: MainViewModel

    //MARK: - Lifecycle methods

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            (application as MarvelPeopleApplication).characterApiClientComponent.inject(this)
            return mainViewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}