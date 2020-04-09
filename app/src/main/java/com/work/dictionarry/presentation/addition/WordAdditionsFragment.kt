package com.work.dictionarry.presentation.addition

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.work.dictionarry.R
import com.work.dictionarry.presentation.WordAdditionsFragmentArgs
import kotlinx.android.synthetic.main.fragment_word_info.*

class WordAdditionsFragment: Fragment(R.layout.fragment_word_addition) {

    private val args: WordAdditionsFragmentArgs by navArgs()
    private val viewModel: WordAdditionsViewModel by lazy { ViewModelProvider(viewModelStore, ViewModelProvider.NewInstanceFactory())[WordAdditionsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when(args.type) {
            AdditionType.SYNONYM -> viewModel.findSynonyms(args.word)
            AdditionType.RHYMES -> viewModel.findRhymes(args.word)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    enum class AdditionType {
        SYNONYM,
        RHYMES
    }
}