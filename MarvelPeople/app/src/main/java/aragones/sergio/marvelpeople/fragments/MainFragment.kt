/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import aragones.sergio.marvelpeople.R
import aragones.sergio.marvelpeople.adapters.CharactersAdapter
import aragones.sergio.marvelpeople.fragments.base.BaseFragment
import aragones.sergio.marvelpeople.viewmodelfactories.MainViewModelFactory
import aragones.sergio.marvelpeople.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment: BaseFragment(), CharactersAdapter.OnItemClickListener {

    //MARK: - Private properties

    private lateinit var srlCharacters: SwipeRefreshLayout
    private lateinit var rvCharacters: RecyclerView
    private lateinit var viewModel: MainViewModel
    private lateinit var charactersAdapter: CharactersAdapter

    //MARK: - Lifecycle methods

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeUI()
    }

    //MARK: - Interface methods

    override fun onItemClick(characterId: Int) {

        val params = mapOf("characterId" to characterId)
        //TODO go to character detail
    }

    //MARK: - Private methods

    private fun initializeUI() {

        val application = activity?.application ?: return
        srlCharacters = swipe_refresh_layout_characters
        rvCharacters = recycler_view_characters
        viewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        charactersAdapter = CharactersAdapter(
            viewModel.mainCharacters.value ?: mutableListOf(),
            requireContext(),
            this
        )
        setupBindings()

        srlCharacters.setOnRefreshListener {

            viewModel.reloadData()
            viewModel.getCharacters(null)
        }
        rvCharacters.layoutManager = LinearLayoutManager(requireContext())
        rvCharacters.adapter = charactersAdapter
        rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getCharacters(null)
                }
            }
        })

        viewModel.getCharacters(null)
    }

    private fun setupBindings() {

        viewModel.mainCharacters.observe(requireActivity(), { charactersResponse ->

            if (charactersResponse.isEmpty()) {
                charactersAdapter.resetList()
            } else {
                charactersAdapter.addCharacters(charactersResponse)
            }
        })

        viewModel.mainLoading.observe(requireActivity(), { isLoading ->
            srlCharacters.isRefreshing = isLoading
        })

        viewModel.mainError.observe(requireActivity(), { error ->
            manageError(error)
        })
    }
}