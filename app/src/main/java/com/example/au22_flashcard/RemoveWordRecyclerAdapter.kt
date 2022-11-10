package com.example.au22_flashcard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemoveWordRecyclerAdapter (val context : Context, val wordList : MutableList<Word>) :
        RecyclerView.Adapter<RemoveWordRecyclerAdapter.ViewHolder>()
{

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)


    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.recyclerlayout_word, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = wordList[position]

        holder.wordInSwedish.text = word.swedish
        holder.wordInEnglish.text = word.english


    }

    override fun getItemCount() = wordList.size




    inner class ViewHolder (itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val wordInSwedish = itemView.findViewById<TextView>(R.id.remove_sweTextView)
        val wordInEnglish = itemView.findViewById<TextView>(R.id.remove_enTextview)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    fun removeItemFromReyclerView(positon : Int){
        wordList[positon]
        notifyDataSetChanged()
    }

}