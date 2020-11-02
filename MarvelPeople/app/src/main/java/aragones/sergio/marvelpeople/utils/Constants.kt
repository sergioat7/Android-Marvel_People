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
        const val LIMIT_PARAM = "limit"
        const val OFFSET_PARAM = "offset"
        const val NAME_STARTS_WITH_PARAM = "nameStartsWith"
        const val LIMIT = 20
        val SUBSCRIBER_SCHEDULER: Scheduler = Schedulers.io()
        val OBSERVER_SCHEDULER: Scheduler = AndroidSchedulers.mainThread()

        fun getDefaultQueryParams(): MutableMap<String, String> {

            val queryParams: MutableMap<String, String> = HashMap()
            queryParams["apikey"] = "5ed58006f51feb029d1240da98b047c6"
            queryParams["ts"] = "1"
            queryParams["hash"] = "050b4ab4753325c5f5b3538c50e5c4e7"
            return queryParams
        }
    }
}
