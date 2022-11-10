package com.example.au22_flashcard

import androidx.annotation.RequiresPermission.Read
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    //add
    @Insert
    fun insert(word : Word)

    //delete
    @Delete
    fun delete(word : Word)

    //getAllwords
    @Query("SELECT * FROM word_table" )
    fun getAllWords() : List<Word>


}