<<<<<<< Updated upstream
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
=======
package com.example.triviaGame.dao

import androidx.room.*
import com.example.triviaGame.entities.PlayerEntity
>>>>>>> Stashed changes

@Dao
interface TriviaDao {

    @Query("DELETE FROM TRIVIA_GAME")
    suspend fun clear(): Int

    @Delete
    suspend fun removePlayer(vacationEntity: PlayerEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: PlayerEntity): Long

    @Query("SELECT * FROM TRIVIA_GAME")
    fun getAll(): List<PlayerEntity>

    @Update
    fun updateScores(account: PlayerEntity)



}