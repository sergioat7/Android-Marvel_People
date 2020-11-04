/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/11/2020
 */

package aragones.sergio.marvelpeople.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.inputmethod.InputMethodManager
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import aragones.sergio.marvelpeople.models.CharacterResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

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
        private const val PRIVATE_KEY = "659fd58aced58e8705daca11124bb52b8da3e47a"
        private const val PUBLIC_KEY = "5ed58006f51feb029d1240da98b047c6"
        val SUBSCRIBER_SCHEDULER: Scheduler = Schedulers.io()
        val OBSERVER_SCHEDULER: Scheduler = AndroidSchedulers.mainThread()

        fun getDefaultQueryParams(): MutableMap<String, String> {

            val timestamp = System.currentTimeMillis().toString()
            val string = timestamp + PRIVATE_KEY + PUBLIC_KEY
            val hash = MessageDigest.getInstance("MD5").digest(string.toByteArray(UTF_8)).joinToString(
                ""
            ) { "%02x".format(it) }

            val queryParams: MutableMap<String, String> = HashMap()
            queryParams["apikey"] = PUBLIC_KEY
            queryParams["ts"] = timestamp
            queryParams["hash"] = hash

            return queryParams
        }

        fun getThumbnailUrl(characterResponse: CharacterResponse): String {
            return (characterResponse.thumbnail.path + "." + characterResponse.thumbnail.extension).replace("http", "https")
        }

        // MARK: - Character list

        const val IMAGE_CORNER = 40.0F;

        fun getRoundImageView(
            image: Drawable,
            context: Context,
            radius: Float
        ): RoundedBitmapDrawable? {

            val imageBitmap = (image as BitmapDrawable).bitmap
            val imageDrawable = RoundedBitmapDrawableFactory.create(context.resources, imageBitmap)
            imageDrawable.isCircular = true
            if (radius > 0) {
                imageDrawable.cornerRadius = radius
            } else {
                imageDrawable.cornerRadius = imageBitmap.width.coerceAtLeast(imageBitmap.height) / 2.0f
            }
            return imageDrawable
        }

        fun hideSoftKeyboard(activity: Activity) {

            activity.currentFocus?.let { currentFocus ->

                val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
            } ?: return
        }

        // MARK: - Character detail

        const val CHARACTER_ID = "characterId"
    }
}
