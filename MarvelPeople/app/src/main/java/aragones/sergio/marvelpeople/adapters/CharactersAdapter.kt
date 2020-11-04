/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aragones.sergio.marvelpeople.R
import aragones.sergio.marvelpeople.models.CharacterResponse
import aragones.sergio.marvelpeople.viewholders.CharactersViewHolder
import java.util.*

class CharactersAdapter(
    var characters: MutableList<CharacterResponse>,
    private val context: Context,
    private var onItemClickListener: OnItemClickListener
): RecyclerView.Adapter<CharactersViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {

        val itemView: View = LayoutInflater.from(parent.context).inflate(
            R.layout.character_item,
            parent,
            false
        )
        return CharactersViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {

        val character = characters[position]

        holder.fillData(character, context)

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(character.id)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(characterId: Int)
    }

    //MARK: - Public methods

    fun addCharacters(newCharacters: MutableList<CharacterResponse>) {

        val position: Int = this.characters.size
        this.characters = newCharacters
        notifyItemInserted(position)
    }

    fun resetList() {

        this.characters = ArrayList<CharacterResponse>()
        notifyDataSetChanged()
    }
}