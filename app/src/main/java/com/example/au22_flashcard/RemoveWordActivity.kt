package com.example.au22_flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RemoveWordActivity : AppCompatActivity(), CoroutineScope {
    lateinit var removeBtn : Button
    lateinit var removeWord : EditText
    lateinit var job : Job
    lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_word)
        db = AppDatabase.getInstance(this)
        job = Job()
        removeWord = findViewById(R.id.removeWord_EditText)
        removeBtn = findViewById(R.id.removeWord_removeWordsBtn)

        removeBtn.setOnClickListener {
            removeWord()
        }
    }


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    fun removeWord(){
       //Query som letar efter string och inte
        launch(Dispatchers.IO){

        }
    }
}