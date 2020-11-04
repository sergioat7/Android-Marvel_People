/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import aragones.sergio.marvelpeople.R
import aragones.sergio.marvelpeople.activities.CharacterDetailActivity
import aragones.sergio.marvelpeople.adapters.CharactersAdapter
import aragones.sergio.marvelpeople.fragments.base.BaseFragment
import aragones.sergio.marvelpeople.utils.Constants
import aragones.sergio.marvelpeople.viewmodelfactories.MainViewModelFactory
import aragones.sergio.marvelpeople.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment: BaseFragment(), CharactersAdapter.OnItemClickListener {

    //MARK: - Private properties

    private lateinit var srlCharacters: SwipeRefreshLayout
    private lateinit var rvCharacters: RecyclerView
    private lateinit var ivNoResults: ImageView
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
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeUI()
    }

    //MARK: - Interface methods

    override fun onItemClick(characterId: Int) {

        val params = mapOf(Constants.CHARACTER_ID to characterId)
        launchActivityWithExtras(CharacterDetailActivity::class.java, params)
    }

    //MARK: - Public methods

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()
        inflater.inflate(R.menu.main_menu, menu)
        setupSearchView(menu)
    }

    //MARK: - Private methods

    private fun initializeUI() {

        val application = activity?.application ?: return
        srlCharacters = swipe_refresh_layout_characters
        rvCharacters = recycler_view_characters
        ivNoResults = image_view_no_results
        viewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        charactersAdapter = CharactersAdapter(
            viewModel.mainCharacters.value ?: mutableListOf(),
            requireContext(),
            this
        )
        setupBindings()

        srlCharacters.setOnRefreshListener {

            viewModel.reloadData()
            viewModel.getCharacters()
        }
        rvCharacters.layoutManager = LinearLayoutManager(requireContext())
        rvCharacters.adapter = charactersAdapter
        rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getCharacters()
                }
            }
        })

        viewModel.getCharacters()
    }

    private fun setupBindings() {

        viewModel.mainCharacters.observe(requireActivity(), { charactersResponse ->

            if (charactersResponse.isEmpty()) {

                charactersAdapter.resetList()
                ivNoResults.visibility = View.VISIBLE
            } else {

                charactersAdapter.addCharacters(charactersResponse)
                ivNoResults.visibility = View.GONE
            }
        })

        viewModel.mainLoading.observe(requireActivity(), { isLoading ->
            srlCharacters.isRefreshing = isLoading
        })

        viewModel.mainError.observe(requireActivity(), { error ->
            manageError(error)
        })
    }

    private fun setupSearchView(menu: Menu) {

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager?
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            searchView.isIconified = false
            searchView.isIconifiedByDefault = false
            searchView.queryHint = resources.getString(R.string.search_characters)
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {

                    if (newText.isEmpty()) {
                        searchCharacters(null)
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {

                    searchCharacters(query)
                    return true
                }
            })
        }
        val searchPlateId =
            searchView.context.resources.getIdentifier("android:id/search_plate", null, null)
        val searchPlate = searchView.findViewById<View>(searchPlateId)
        if (searchPlate != null) {
            val searchTextId = searchPlate.context.resources.getIdentifier(
                "android:id/search_src_text",
                null,
                null
            )
            val searchText = searchPlate.findViewById<TextView>(searchTextId)
            if (searchText != null) {
                searchText.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                searchText.setHintTextColor(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.white
                    )
                )
            }
        }
    }

    private fun searchCharacters(search: String?) {

        viewModel.setSearch(search)
        viewModel.reloadData()
        viewModel.getCharacters()
        Constants.hideSoftKeyboard(requireActivity())
    }
}