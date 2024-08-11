package com.example.launchmanagementsystem.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.launchmanagementsystem.Model.Attendance
import java.util.Date

@Dao
interface AttendanceDao {
    @Insert
    suspend fun insertAttendance(attendance: Attendance)

    @Query("SELECT * FROM attendance WHERE date = :date")
    fun getAttendanceByDate(date: Date): LiveData<List<Attendance>>

    @Query("SELECT * FROM attendance WHERE employeeId = :employeeId")
    fun getAttendanceByEmployee(employeeId: Int): LiveData<List<Attendance>>
}
