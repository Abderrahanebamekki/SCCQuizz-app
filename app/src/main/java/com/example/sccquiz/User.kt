package com.example.sccquiz

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.annotations.NotNull
import kotlin.random.Random
@Entity(tableName = "User")
class User(
     email : String? = null,
     password : String? = null,
     userName : String? = null ,
    isLastUser : Boolean? = null
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserId")
    private var _id: Int? = 1
    var id : Int?
        get() = _id
        set(value) {
            _id = value
        }

    @ColumnInfo(name = "UserEmail")
    private var _email = email
    var  email : String?
        get() = _email
        set(value) {
            _email = value
        }

    private var _password = password
    var  password : String?
        get() = _password
        set(value) {
            _password = value
        }

    @ColumnInfo(name = "UserName")
    private var _userName = userName
    var  userName : String?
        get() = _userName
        set(value) {
            _userName = value
        }

    @ColumnInfo(name = "isLastUser")
    private var _isLastUser = isLastUser
    var  isLastUser  : Boolean
        get() = _isLastUser == true
        set(value) {
            _isLastUser = value
        }

    fun register(
        context: Context,
        navController: NavController
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val db = ConvertViewModel()
                    db.saveData(this , context)
                    navController.navigate(route = "Home/${_userName}")
                    //Toast.makeText(context, "it.exception?.message", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }



    fun login(
        context: Context,
        navController: NavController ,
        username: String?
    ) {
        if (email != null || password != null) {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        navController.navigate(route = "Home/${username}")
                        //Toast.makeText(context, "it.exception?.message", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(context, " Please enter password and email ", Toast.LENGTH_SHORT).show()
        }
    }


    fun viewQuiz() {}
    fun answerQuiz() {}
    fun submitQuiz() {}
    fun viewResult() {}
    fun viewLeaderBoard() {}
    fun shareApp() {}
}