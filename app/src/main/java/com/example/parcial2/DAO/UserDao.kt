package com.example.parcial2.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parcial2.Model.User


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteById(userId: Int):Int

    @Query("UPDATE users SET nombre = :nombre, apellido = :apellido, edad = :edad WHERE id = :userId")
    suspend fun updateById(userId: Int, nombre: String, apellido: String, edad: Int): Int


}