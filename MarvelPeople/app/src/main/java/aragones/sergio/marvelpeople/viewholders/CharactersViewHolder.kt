/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.viewholders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import aragones.sergio.marvelpeople.R
import aragones.sergio.marvelpeople.models.CharacterResponse
import aragones.sergio.marvelpeople.utils.Constants
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item.view.*

class CharactersViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun fillData(character: CharacterResponse, context: Context) {

        itemView.text_view_name.text = character.name
        itemView.text_view_description.text = character.description

        val imageView = itemView.image_view_character
        val loading = itemView.progress_bar_loading
        loading.visibility = View.VISIBLE
        val imageUrl = character.thumbnail.path + "." + character.thumbnail.extension
        Picasso
            .get()
            .load(imageUrl)
            .fit()
            .centerCrop()
            .error(R.drawable.no_image)
            .into(imageView, object: Callback {

                override fun onSuccess() {

                    imageView.setImageDrawable(
                        Constants.getRoundImageView(
                            imageView.drawable,
                            context,
                            Constants.IMAGE_CORNER
                        )
                    )
                    loading.visibility = View.GONE
                }

                override fun onError(e: Exception) {
                    loading.visibility = View.GONE
                }
            })
    }
}