/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/11/2020
 */

package aragones.sergio.marvelpeople.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class Constants {
    companion object {

        // MARK: - Retrofit constants

        const val BASE_ENDPOINT = "https://gateway.marvel.com/v1/public/"
        const val CONNECT_TIMEOUT: Long = 60
        const val READ_TIMEOUT: Long = 30
        const val WRITE_TIMEOUT: Long = 15
        const val API_KEY_PARAM = "apikey";
        const val TS_PARAM = "ts";
        const val HASH_PARAM = "hash";
        const val LIMIT_PARAM = "limit";
        const val OFFSET_PARAM = "offset";
        const val NAME_STARTS_WITH_PARAM = "nameStartsWith";
        val SUBSCRIBER_SCHEDULER: Scheduler = Schedulers.io()
        val OBSERVER_SCHEDULER: Scheduler = AndroidSchedulers.mainThread()
    }
}
