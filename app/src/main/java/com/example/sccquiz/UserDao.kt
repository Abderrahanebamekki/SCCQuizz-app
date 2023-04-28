package com.example.sccquiz

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertUser(user : User)

    @Query("SELECT UserEmail FROM User WHERE isLastUser = :lastUser")
    fun findUser(lastUser : Boolean): String

    @Query("DELETE FROM User WHERE UserEmail = :email")
    fun deleteUser(email: String)

    @Query("SELECT UserName FROM User WHERE UserEmail = :email")
    fun findUserByEmail(email: String) : String

}