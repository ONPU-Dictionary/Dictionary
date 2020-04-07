package com.work.dictionarry.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.work.dictionarry.R
import kotlinx.android.synthetic.main.fragment_word_info.*

class WordAdditionsFragment: Fragment(R.layout.fragment_word_addition) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }
}