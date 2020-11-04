/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 4/11/2020
 */

package aragones.sergio.marvelpeople.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import aragones.sergio.marvelpeople.R
import aragones.sergio.marvelpeople.fragments.base.BaseFragment
import aragones.sergio.marvelpeople.models.CharacterResponse
import aragones.sergio.marvelpeople.utils.Constants
import aragones.sergio.marvelpeople.viewmodelfactories.CharacterDetailViewModelFactory
import aragones.sergio.marvelpeople.viewmodels.CharacterDetailViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_detail_fragment.*

class CharacterDetailFragment: BaseFragment() {

    //MARK: - Private properties

    private var characterId: Int? = null
    private lateinit var ivCharacter: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var pbLoading: ProgressBar
    private lateinit var viewModel: CharacterDetailViewModel

    //MARK: - Lifecycle methods

    companion object {
        fun newInstance() = CharacterDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        characterId = this.arguments?.getInt(Constants.CHARACTER_ID)
        return inflater.inflate(R.layout.character_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeUI()
    }

    //MARK: - Private methods

    private fun initializeUI() {

        val application = activity?.application ?: return
        ivCharacter = image_view_character
        tvName = text_view_name
        tvDescription = text_view_description
        pbLoading = progress_bar_loading
        viewModel = ViewModelProvider(this, CharacterDetailViewModelFactory(application)).get(CharacterDetailViewModel::class.java)
        setupBindings()

        characterId?.let {
            viewModel.getCharacter(it)
        }
    }

    private fun setupBindings() {

        viewModel.characterDetailCharacter.observe(requireActivity(), { characterResponse ->
            setupData(characterResponse)
        })

        viewModel.characterDetailError.observe(requireActivity(), { error ->
            manageError(error)
        })
    }

    private fun setupData(characterResponse: CharacterResponse) {

        val imageUrl = Constants.getThumbnailUrl(characterResponse)
        Picasso
            .get()
            .load(imageUrl)
            .fit()
            .centerCrop()
            .error(R.drawable.no_image)
            .into(ivCharacter, object: Callback {

                override fun onSuccess() {
                    pbLoading.visibility = View.GONE
                }

                override fun onError(e: Exception) {
                    pbLoading.visibility = View.GONE
                }
            })

        tvName.text = characterResponse.name
        tvDescription.text = characterResponse.description
    }
}