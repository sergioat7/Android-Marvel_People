/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.viewmodels

import androidx.lifecycle.ViewModel
import aragones.sergio.marvelpeople.repositories.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val mainRepository: MainRepository
): ViewModel() {
    // TODO: Implement the ViewModel
}