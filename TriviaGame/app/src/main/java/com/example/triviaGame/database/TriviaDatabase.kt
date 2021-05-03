package com.example.triviaGame.database

import com.example.triviaGame.entities.PlayerEntity
import com.example.triviaGame.dao.TriviaDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PlayerEntity::class], version = 9,
    exportSchema = false)
abstract class TriviaDatabase:RoomDatabase() {
    abstract val TriviaDao:TriviaDao

    companion object {
        @Volatile
        private var INSTANCE: TriviaDatabase? = null

        fun getInstance(context: Context): TriviaDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TriviaDatabase::class.java,
                        "trivia_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}