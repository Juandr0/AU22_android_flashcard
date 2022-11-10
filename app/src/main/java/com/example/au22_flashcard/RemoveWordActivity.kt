package com.example.au22_flashcard

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RemoveWordActivity : AppCompatActivity(), CoroutineScope {
    lateinit var job : Job
    lateinit var db : AppDatabase
    lateinit var goBackBtn : Button
    lateinit var trashCan : ImageView
    var wordList = mutableListOf<Word>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_word)
        goBackBtn = findViewById(R.id.remove_backButton)
        trashCan = findViewById(R.id.remove_trashcan)
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

    fun trashCanAnimation(){
        trashCan.animate().apply {
            duration = 1000
            rotationYBy(360f)
        }.start()
    }

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
                    trashCanAnimation()
            }
        })
    }


    fun removeWord(wordToDelete : Word){
        launch(Dispatchers.IO){
            db.wordDao.delete(wordToDelete)

        }
    }
}