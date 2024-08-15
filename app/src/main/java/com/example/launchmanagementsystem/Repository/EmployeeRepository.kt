package com.example.launchmanagementsystem.Repository

import androidx.lifecycle.LiveData
import com.example.launchmanagementsystem.DAO.EmployeeDao
import com.example.launchmanagementsystem.Model.Employee

class EmployeeRepository(private val employeeDao: EmployeeDao) {
    suspend fun insert(employee: Employee) {
        employeeDao.insert(employee)
    }
}
