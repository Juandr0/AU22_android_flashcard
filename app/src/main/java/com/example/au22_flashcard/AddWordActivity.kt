package com.example.au22_flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddWordActivity : AppCompatActivity(), CoroutineScope {

    lateinit var saveBtn : Button
    lateinit var backBtn : Button
    lateinit var enEditText : EditText
    lateinit var svEditText : EditText
    lateinit var db : AppDatabase
    private lateinit var job : Job

    override val coroutineContext: CoroutineContext
        get() =Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

        saveBtn = findViewById(R.id.addWord_saveButton)
        backBtn = findViewById(R.id.addWord_backButton)
        enEditText = findViewById(R.id.addWord_englishEditText)
        svEditText = findViewById(R.id.addWord_swedishEditText)

        job = Job()
        db = AppDatabase.getInstance(this)



        backBtn.setOnClickListener {
            finish()
        }

        saveBtn.setOnClickListener {
            saveWordToDb()
            resetEditText()
        }
    }

    private fun resetEditText(){
        enEditText.text.clear()
        svEditText.text.clear()
    }
    //Lägger in nya ordet i databasen
    private fun saveWordToDb(){

        if (enEditText.text.isEmpty() || svEditText.text.isEmpty() ) {
            Log.d("!!!", "Fileds cannot be empty when adding a new word!")
        } else {
            var englishWord = enEditText.text.toString()
            var swedishWord = svEditText.text.toString()
            var newWord = Word(englishWord, swedishWord)

            launch(Dispatchers.IO){
                db.wordDao.insert(newWord)
            }
        }
    }
}