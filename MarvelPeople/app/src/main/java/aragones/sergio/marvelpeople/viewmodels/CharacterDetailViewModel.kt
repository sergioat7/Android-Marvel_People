/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 4/11/2020
 */

package aragones.sergio.marvelpeople.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import aragones.sergio.marvelpeople.R
import aragones.sergio.marvelpeople.models.CharacterResponse
import aragones.sergio.marvelpeople.models.ErrorResponse
import aragones.sergio.marvelpeople.network.apiclient.APIClient
import aragones.sergio.marvelpeople.repositories.CharacterDetailRepository
import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.HttpException
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val characterDetailRepository: CharacterDetailRepository
): ViewModel() {

    //MARK: - Private properties

    private val _characterDetailCharacter = MutableLiveData<CharacterResponse>()
    private val _characterDetailError = MutableLiveData<ErrorResponse>()

    //MARK: - Public properties

    val characterDetailCharacter: LiveData<CharacterResponse> = _characterDetailCharacter
    val characterDetailError: LiveData<ErrorResponse> = _characterDetailError

    //MARK: - Public methods

    fun getCharacter(characterId: Int) {

        characterDetailRepository.getCharacterObserver(characterId).subscribeBy(
            onSuccess = { charactersDataResponse ->
                _characterDetailCharacter.value = charactersDataResponse.data.results[0]
            },
            onError = { error ->

                if (error is HttpException) {
                    error.response()?.errorBody()?.let { errorBody ->

                        _characterDetailError.value = APIClient.gson.fromJson(
                            errorBody.charStream(), ErrorResponse::class.java
                        )
                    } ?: run {
                        _characterDetailError.value = ErrorResponse("", R.string.error_server)
                    }
                } else {
                    _characterDetailError.value = ErrorResponse("", R.string.error_server)
                }
            }
        )
    }
}