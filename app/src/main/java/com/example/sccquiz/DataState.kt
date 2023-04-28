package com.example.sccquiz

sealed class DataState{
    class Success(val data : MutableList<Quiz> ) : DataState()
    class Failure(val message : String): DataState()
    object Loading : DataState()
    object Empty :DataState()
}
