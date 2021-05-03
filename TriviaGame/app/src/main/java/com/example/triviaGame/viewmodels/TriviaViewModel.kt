package com.example.triviaGame.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.triviaGame.dao.TriviaDao
import com.example.triviaGame.entities.PlayerEntity
import kotlinx.coroutines.launch

class TriviaViewModel(
    val database: TriviaDao,
    application: Application
): AndroidViewModel(application) {

    //launch coroutine to insert a new player
    fun insertPlayer(playerEntity: PlayerEntity): Boolean {
        var result: Boolean = false
        viewModelScope.launch {
            result = insertPlayerInDatabase(playerEntity)
        }
        return result
    }

    fun getScoresFor(playerEntity: PlayerEntity):
            List<PlayerEntity> {
        var result = listOf(PlayerEntity())
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            val players:ArrayList<PlayerEntity> = arrayListOf()
            var i = 0
            for (entry in result) {
                if (entry.username == playerEntity.username) {
                    players.add(entry)
                    i++
                }
            }
            return players
        }
        return listOf(PlayerEntity())
    }

    fun deleteAllAccounts() {
        viewModelScope.launch {
            deleteAllAccountsInDatabase()
        }
    }

    private suspend fun insertPlayerInDatabase(playerEntity:
                                               PlayerEntity): Boolean {
        return database.insert(playerEntity) > 0
    }

    //returns true if an account with the given username is already
    //present in the database
    fun containsAccount(playerEntity: PlayerEntity): Boolean {
        var result = listOf(PlayerEntity())
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            for (entry in result) {
                if (entry.username == playerEntity.username)
                    return true
            }
        }
        return false
    }

    //returns true if both username and password of account match
    fun checkLogin(playerEntity: PlayerEntity): Boolean {
        var result = listOf<PlayerEntity>()
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            for (entry in result) {
                if (entry.username == playerEntity.username
                    && entry.password == playerEntity.password)
                    return true
            }
        }
        return false
    }

    fun getCurrentUser(): PlayerEntity {
        var result = listOf(PlayerEntity())
        var user = PlayerEntity()
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            for (entry in result) {
                if (entry.currentAccount)
                    user = entry
            }
        }
        return user
    }

    fun setCurrentUser(playerEntity: PlayerEntity) {
        viewModelScope.launch {
            removeAccount(playerEntity)
            playerEntity.currentAccount = true
            insertPlayer(playerEntity)
        }
    }

    fun removeAccount(playerEntity: PlayerEntity): Boolean {
        var result = listOf<PlayerEntity>()
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            for (entry in result) {
                if (entry.username == playerEntity.username &&
                    entry.password == playerEntity.password) {
                    viewModelScope.launch {
                        deleteVacationInDatabase(playerEntity)
                    }
                }
            }
        }
        var result2 = listOf<PlayerEntity>()
        viewModelScope.launch {
            result2 = getAllInDatabase()
        }
        return result.size != result2.size
    }

    fun removeCurrentUser() {
        var result = listOf(PlayerEntity())
        var user:PlayerEntity
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            for (entry in result) {
                if (entry.currentAccount) {
                    removeAccount(entry)
                    user = entry
                    user.currentAccount = false
                    insertPlayer(user)
                }
            }
        }
    }

    private suspend fun deleteAllAccountsInDatabase(): Boolean {
        return database.clear() > 0
    }

    private suspend fun deleteVacationInDatabase(vacationEntity: PlayerEntity): Int {
        return database.removePlayer(vacationEntity)
    }

    private suspend fun getAllInDatabase(): List<PlayerEntity> {
        return database.getAll()
    }
}