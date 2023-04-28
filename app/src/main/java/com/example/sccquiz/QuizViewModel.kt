package com.example.sccquiz

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class QuizViewModel : ViewModel() {
        val response : MutableState<DataState> = mutableStateOf(DataState.Empty)
        init {
            fetchDataFromFirebase()
        }
       private val tempList = mutableListOf<Quiz>()
        private fun fetchDataFromFirebase(){
            response.value = DataState.Loading
            FirebaseDatabase.getInstance().getReference("Quiz")
                .addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for (dataSnap in snapshot.children){
                                val queItem = dataSnap.getValue(Quiz::class.java)
                                if (queItem != null && check(queItem = queItem)){
                                    tempList.add(queItem)
                                }
                            }
                            response.value = DataState.Success(tempList)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            response.value = DataState.Failure(error.message)
                        }

                    }
                )
        }

        private fun check( queItem : Quiz ) : Boolean {
            for (item in tempList){
                if (queItem == item){
                    return false
                }
            }
            return true
        }
}

