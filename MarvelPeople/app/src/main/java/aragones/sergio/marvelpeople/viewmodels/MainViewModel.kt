/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import aragones.sergio.marvelpeople.R
import aragones.sergio.marvelpeople.models.CharacterResponse
import aragones.sergio.marvelpeople.models.ErrorResponse
import aragones.sergio.marvelpeople.network.apiclient.APIClient
import aragones.sergio.marvelpeople.repositories.MainRepository
import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.HttpException
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val mainRepository: MainRepository
): ViewModel() {

    //MARK: - Private properties

    private var page: Int = 1
    private val _mainCharacters = MutableLiveData<MutableList<CharacterResponse>>()
    private val _mainLoading = MutableLiveData<Boolean>()
    private val _mainError = MutableLiveData<ErrorResponse>()

    //MARK: - Public properties

    val mainCharacters: LiveData<MutableList<CharacterResponse>> = _mainCharacters
    val mainLoading: LiveData<Boolean> = _mainLoading
    val mainError: LiveData<ErrorResponse> = _mainError

    //MARK: - Public methods

    fun getCharacters(search: String?) {

        _mainLoading.value = true
        mainRepository.getCharactersObserver(page, search).subscribeBy(
                onSuccess = { charactersDataResponse ->

                    page++
                    _mainLoading.value = false
                    val currentValues = _mainCharacters.value ?: mutableListOf()
                    currentValues.addAll(charactersDataResponse.data.results)
                    _mainCharacters.value = currentValues
                },
                onError = { error ->

                    _mainLoading.value = false
                    if (error is HttpException) {
                        error.response()?.errorBody()?.let { errorBody ->

                            _mainError.value = APIClient.gson.fromJson(
                                    errorBody.charStream(), ErrorResponse::class.java
                            )
                        } ?: run {
                            _mainError.value = ErrorResponse("", R.string.error_server)
                        }
                    } else {
                        _mainError.value = ErrorResponse("", R.string.error_server)
                    }
                }
        )
    }

    fun reloadData() {

        page = 1
        _mainCharacters.value = mutableListOf()
    }
}