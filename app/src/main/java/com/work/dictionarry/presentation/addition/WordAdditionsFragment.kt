package com.work.dictionarry.presentation.addition

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.work.dictionarry.R
import kotlinx.android.synthetic.main.fragment_word_addition.*

class WordAdditionsFragment : Fragment(R.layout.fragment_word_addition) {

    private val args: WordAdditionsFragmentArgs by navArgs()
    private val viewModel: WordAdditionsViewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            ViewModelProvider.NewInstanceFactory()
        )[WordAdditionsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (AdditionType.values()[args.type]) {
            AdditionType.SYNONYM -> viewModel.findSynonyms(args.word)
            AdditionType.RHYMES -> viewModel.findRhymes(args.word)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        word.text = args.word
        toolbar.title = getString(AdditionType.values()[args.type].readableName)
        additionsList.layoutManager = LinearLayoutManager(requireContext())
        additionsList.adapter = AdditionsListAdapter(this::onAdditionClicked)
        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it) {
                WordAdditionsViewModel.LoadingState.Loading -> toggleLoadingView(true)
                WordAdditionsViewModel.LoadingState.Error -> toggleLoadingView(true)
                is WordAdditionsViewModel.LoadingState.Loaded -> {
                    toggleLoadingView(false)
                    (additionsList.adapter as AdditionsListAdapter).additions = it.data.getData()
                }
            }
        })
    }

    private fun onAdditionClicked(word: String) {
        findNavController().navigate(WordAdditionsFragmentDirections.additionFragmentAction(word))
    }

    private fun toggleLoadingView(isLoading: Boolean) {
        progress.isVisible = isLoading
        group.isVisible = !isLoading
    }

    enum class AdditionType(val readableName: Int) {
        SYNONYM(R.string.synonyms),
        RHYMES(R.string.rhymes)
    }
}