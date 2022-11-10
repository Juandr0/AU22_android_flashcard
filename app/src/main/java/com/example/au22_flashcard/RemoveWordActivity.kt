package com.example.au22_flashcard

import android.content.Context
import android.content.Intent
import android.graphics.DashPathEffect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RemoveWordActivity : AppCompatActivity(), CoroutineScope {
    lateinit var job : Job
    lateinit var db : AppDatabase
    lateinit var goBackBtn : Button
    var wordList = mutableListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_word)
        goBackBtn = findViewById(R.id.remove_backButton)
        db = AppDatabase.getInstance(this)
        job = Job()


        goBackBtn.setOnClickListener {
            finish()
        }




    }

    override fun onResume() {
        super.onResume()
        launch {
            val tempList = getList()
            wordList = tempList.await() as MutableList<Word>
            initializeRecyclerView(wordList)
        }

    }



    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    fun getList() : Deferred<List<Word>> =
        async(Dispatchers.IO) {
            db.wordDao.getAllWords()
        }


    fun initializeRecyclerView(wordList : MutableList<Word>) {

        val recyclerView = findViewById<RecyclerView>(R.id.remove_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RemoveWordRecyclerAdapter(this, wordList)
        recyclerView.adapter = adapter


        adapter.setOnItemClickListener(object : RemoveWordRecyclerAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                    removeWord(wordList[position])
                    adapter.removeItemFromReyclerView(position)
                    wordList.removeAt(position)
            }
        })
    }


    fun removeWord(wordToDelete : Word){
        launch(Dispatchers.IO){
            db.wordDao.delete(wordToDelete)

        }
    }
}