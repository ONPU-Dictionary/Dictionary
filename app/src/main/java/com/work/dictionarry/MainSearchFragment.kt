package com.work.dictionarry

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment

class MainSearchFragment: Fragment(R.layout.fragment_main_search), SearchView.OnQueryTextListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val searchItem: SearchView = menu.findItem(R.id.search).actionView as SearchView
        searchItem.queryHint = getString(R.string.find_word)
        searchItem.setOnQueryTextListener(this)
        searchItem.isIconified = false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}