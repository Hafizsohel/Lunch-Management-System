package com.example.launchmanagementsystem.Repository

import androidx.lifecycle.LiveData
import com.example.launchmanagementsystem.DAO.EmployeeDao
import com.example.launchmanagementsystem.Model.Employee

class EmployeeRepository(private val employeeDao: EmployeeDao) {
    suspend fun insert(employee: Employee) {
        employeeDao.insert(employee)
    }

    suspend fun update(employee: Employee) {
        employeeDao.update(employee)
    }

    fun getPresentEmployees(): LiveData<List<Employee>> {
        return employeeDao.getPresentEmployees()
    }
}
