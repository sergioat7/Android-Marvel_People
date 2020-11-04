/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 4/11/2020
 */

package aragones.sergio.marvelpeople.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import aragones.sergio.marvelpeople.R
import aragones.sergio.marvelpeople.fragments.CharacterDetailFragment
import aragones.sergio.marvelpeople.utils.Constants

class CharacterDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_detail_activity)

        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val characterId = intent.getIntExtra(Constants.CHARACTER_ID, 0)
        val characterDetailFragment = CharacterDetailFragment.newInstance()
        if (characterId > 0) {

            val bundle = Bundle()
            bundle.putInt(Constants.CHARACTER_ID, characterId)
            characterDetailFragment.arguments = bundle
        }

        if (savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, characterDetailFragment)
                .commitNow()
        }
    }
}