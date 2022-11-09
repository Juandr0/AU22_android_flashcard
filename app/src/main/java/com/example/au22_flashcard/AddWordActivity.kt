package com.example.au22_flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddWordActivity : AppCompatActivity() {

    lateinit var saveBtn : Button
    lateinit var backBtn : Button
    lateinit var enEditText : EditText
    lateinit var svEditText : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        saveBtn = findViewById(R.id.addWord_saveButton)
        backBtn = findViewById(R.id.addWord_backButton)
        enEditText = findViewById(R.id.addWord_englishEditText)
        svEditText = findViewById(R.id.addWord_swedishEditText)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)
    }
}