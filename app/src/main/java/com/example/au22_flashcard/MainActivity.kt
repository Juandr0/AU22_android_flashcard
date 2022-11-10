package com.example.au22_flashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    lateinit var addWordsButton : Button
    lateinit var wordView : TextView
    lateinit var db : AppDatabase
    private lateinit var job : Job

    var currentWord : Word? = null
    val wordList = WordList()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordView = findViewById(R.id.wordTextView)
        addWordsButton = findViewById(R.id.main_addWordsBtn)
        db = AppDatabase.getInstance(this)
        job = Job()
        showNewWord()

        launch{
            var newWordsList = getDbList()
            val list = newWordsList.await()
            addWords(list)
        }

        wordView.setOnClickListener {
            revealTranslation()
        }

        addWordsButton.setOnClickListener {
            addWordActivity()
        }

    }




    fun getDbList() : Deferred<List<Word>> =
        async(Dispatchers.IO) {
            db.wordDao.getAllWords()
        }




    fun addWords(list : List<Word>){
        for (word in list){
            wordList.addWord(word)
            Log.d("!!!", word.swedish)
        }


    }

    fun addWordActivity() {
        val intent = Intent (this, AddWordActivity::class.java)
        startActivity(intent)

    }

    fun revealTranslation() {
        wordView.text = currentWord?.english
    }


    fun showNewWord() {
        currentWord = wordList.getNewWord()
        wordView.text = currentWord?.swedish
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_UP) {
            showNewWord()
        }

        return true
    }










}

