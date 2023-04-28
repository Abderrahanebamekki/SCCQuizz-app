package com.example.sccquiz

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserRepository (private val userDao : UserDao) {
    val searchResults = MutableLiveData<String>()
    val searchResultUserName = MutableLiveData<String>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    fun insertUser(newUser : User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.insertUser(newUser)
        }
    }

    fun deleteUser(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.deleteUser(name)
        }
    }

    fun findUser(lastUser : Boolean) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(lastUser).await()
        }
    }

    private fun asyncFind(lastUser : Boolean): Deferred<String> =
        coroutineScope.async(Dispatchers.IO) {
            return@async userDao.findUser(lastUser = lastUser)
        }

    fun findUserByEmail(email : String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResultUserName.value = asyncFindByEmail(email).await().toString()
        }
    }

    private fun asyncFindByEmail(email : String): Deferred<String> =
        coroutineScope.async(Dispatchers.IO) {
            return@async userDao.findUserByEmail(email)
        }
}
