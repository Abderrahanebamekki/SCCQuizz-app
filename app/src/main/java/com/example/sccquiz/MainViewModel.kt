package com.example.sccquiz

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel (application: Application): ViewModel() {
    private val _repository: UserRepository
    val repository : UserRepository
        get() = _repository
    private val searchResults: MutableLiveData<String>

    init {
        val userDb = UserRoomDatabase.getInstance(application)
        val userDao = userDb.UserDao()
        _repository = UserRepository(userDao)
        searchResults = _repository.searchResults
    }
    fun insertUser(user:User) {
        _repository.insertUser(user)
    }
    fun findUser(lastUser : Boolean) {
        _repository.findUser(lastUser)
    }

    fun deleteUser(email: String) {
        _repository.deleteUser(email)
    }
    fun findUserByEmail(email: String){
        _repository.findUserByEmail(email)
    }

}