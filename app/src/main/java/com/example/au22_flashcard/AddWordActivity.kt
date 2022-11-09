package com.example.au22_flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Database

class AddWordActivity : AppCompatActivity() {

    lateinit var saveBtn : Button
    lateinit var backBtn : Button
    lateinit var enEditText : EditText
    lateinit var svEditText : EditText
    lateinit var db : AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)


        db = AppDatabase.getInstance(this)

        saveBtn = findViewById(R.id.addWord_saveButton)
        backBtn = findViewById(R.id.addWord_backButton)
        enEditText = findViewById(R.id.addWord_englishEditText)
        svEditText = findViewById(R.id.addWord_swedishEditText)


        backBtn.setOnClickListener {
            finish()
        }

        saveBtn.setOnClickListener {
            saveWordToDb()
        }
    }


    private fun saveWordToDb(){

        if (enEditText.text.isEmpty() || svEditText.text.isEmpty() ) {
            Log.d("!!!", "Fileds cannot be empty when adding a new word!")
        } else {
            var englishWord = enEditText.text.toString()
            var swedishWord = svEditText.text.toString()
            var newWord = Word(englishWord, swedishWord)

            db.wordDao.insert(newWord)
        }

        // lägg nya ordet in i DB
    }
}