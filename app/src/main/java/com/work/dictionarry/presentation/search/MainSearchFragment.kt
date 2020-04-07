package com.work.dictionarry.presentation.search

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.work.dictionarry.R
import kotlinx.android.synthetic.main.fragment_main_search.*

class MainSearchFragment : Fragment(R.layout.fragment_main_search), SearchView.OnQueryTextListener {

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[SearchViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        with(wordsList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ListAdapter(this@MainSearchFragment::onWordClicked)
        }
        viewModel.wordLd.observe(viewLifecycleOwner, Observer { handleLoadingProgress(it) })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            viewModel.findWord(it)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun handleLoadingProgress(progress: SearchViewModel.DataLoadingProgress) {
        when (progress) {
            is SearchViewModel.DataLoadingProgress.Loading -> toggleLoadingView(true)
            is SearchViewModel.DataLoadingProgress.Error -> toggleLoadingView(false)
            is SearchViewModel.DataLoadingProgress.Loaded -> {
                toggleLoadingView(false)
                (wordsList.adapter as ListAdapter).setData(progress.words)
            }
        }
    }

    private fun initToolbar() {
        val searchItem: SearchView = toolbar.menu.findItem(R.id.search).actionView as SearchView
        searchItem.queryHint = getString(R.string.find_word)
        searchItem.setOnQueryTextListener(this)
        searchItem.isIconified = false
    }

    private fun toggleLoadingView(isLoading: Boolean) {
        progress.isVisible = isLoading
        wordsList.isVisible = !isLoading
    }

    private fun onWordClicked(word: String) {
        findNavController().navigate(MainSearchFragmentDirections.searchFragmentAction(word))
    }
}