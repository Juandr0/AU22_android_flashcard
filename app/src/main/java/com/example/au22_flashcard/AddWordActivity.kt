package com.example.au22_flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Database

class AddWordActivity : AppCompatActivity() {

    lateinit var saveBtn : Button
    lateinit var backBtn : Button
    lateinit var enEditText : EditText
    lateinit var svEditText : EditText
    lateinit var db : AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        saveBtn = findViewById(R.id.addWord_saveButton)
        backBtn = findViewById(R.id.addWord_backButton)
        enEditText = findViewById(R.id.addWord_englishEditText)
        svEditText = findViewById(R.id.addWord_swedishEditText)
        db = AppDatabase.getInstance(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

        backBtn.setOnClickListener {
            finish()
        }

        saveBtn.setOnClickListener {
            saveWordToDb()
        }
    }


    private fun saveWordToDb(){
        var englishWord = enEditText.text.toString()
        var swedishWord = svEditText.text.toString()

        // lägg in i DB
    }
}