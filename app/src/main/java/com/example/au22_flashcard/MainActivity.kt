package com.example.au22_flashcard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var addWordsButton : Button
    lateinit var wordView : TextView
    lateinit var db : AppDatabase

    var currentWord : Word? = null
    val wordList = WordList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase.getInstance(this)

        wordView = findViewById(R.id.wordTextView)
        addWordsButton = findViewById(R.id.main_addWordsBtn)

        showNewWord()
        wordView.setOnClickListener {
            revealTranslation()
        }

        addWordsButton.setOnClickListener {
            addWordActivity()
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