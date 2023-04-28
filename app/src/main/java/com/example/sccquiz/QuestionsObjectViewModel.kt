package com.example.sccquiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionsObjectViewModel : ViewModel() {
    private val _selectedListQuestions = MutableLiveData<List<Question?>?>()
    val selectedListQuestions: LiveData<List<Question?>?> get() = _selectedListQuestions

    fun selectObject(listQuestions: List<Question?>?) {
        _selectedListQuestions.value =  listQuestions
    }
}