package com.work.dictionarry.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.dictionarry.R
import com.work.dictionarry.networking.Word
import kotlinx.android.synthetic.main.item_word.view.*

class ListAdapter(private val onWordClicked: (word: String) -> Unit): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val list = mutableListOf<Word>()

    fun setData(list: List<Word>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            word.text = list[position].word
            syllables.text = context.getString(R.string.syllables, list[position].syllables.list.joinToString("-"))
            pronunciation.text = context.getString(R.string.pronunciation, list[position].pronunciation.pronunciation)
            setOnClickListener { onWordClicked(list[position].word) }
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}