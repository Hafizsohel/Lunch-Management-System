package com.example.launchmanagementsystem.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.launchmanagementsystem.DAO.EmployeeDao
import com.example.launchmanagementsystem.Database.EmployeeDatabase
import com.example.launchmanagementsystem.Model.Employee
import com.example.launchmanagementsystem.Repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EmployeeRepository = EmployeeRepository(EmployeeDatabase.getDatabase(application).employeeDao())
    private val employeeDao: EmployeeDao = EmployeeDatabase.getDatabase(application).employeeDao()
    val allEmployees: LiveData<List<Employee>> = employeeDao.getAllEmployees()
    var presentEmployees: LiveData<List<Employee>> = employeeDao.getPresentEmployees()

    fun addEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(employee)
        }
    }
}
