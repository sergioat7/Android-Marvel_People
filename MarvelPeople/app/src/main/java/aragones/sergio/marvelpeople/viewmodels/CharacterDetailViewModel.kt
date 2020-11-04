/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 4/11/2020
 */

package aragones.sergio.marvelpeople.viewmodels

import androidx.lifecycle.ViewModel
import aragones.sergio.marvelpeople.repositories.CharacterDetailRepository
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val characterDetailRepository: CharacterDetailRepository
): ViewModel() {
    // TODO: Implement the ViewModel
}