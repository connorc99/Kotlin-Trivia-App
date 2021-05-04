package com.example.triviaGame.database

import com.example.triviaGame.entities.PlayerEntity

class GlobalUser {
    companion object {
        lateinit var user: PlayerEntity
    }
}