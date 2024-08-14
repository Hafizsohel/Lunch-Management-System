package com.example.launchmanagementsystem.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.launchmanagementsystem.Model.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: Employee)

    @Update
    suspend fun update(employee: Employee)
    @Query("SELECT * FROM employees")
    fun getAllEmployees(): LiveData<List<Employee>>

    @Query("SELECT * FROM employees WHERE isPresent = 1")
    fun getPresentEmployees(): LiveData<List<Employee>>
}

