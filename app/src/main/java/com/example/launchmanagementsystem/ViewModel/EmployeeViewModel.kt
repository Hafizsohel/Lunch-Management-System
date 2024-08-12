package com.example.launchmanagementsystem.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.launchmanagementsystem.Database.EmployeeDatabase
import com.example.launchmanagementsystem.Model.Employee
import com.example.launchmanagementsystem.Repository.EmployeeRepository
import kotlinx.coroutines.launch
class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EmployeeRepository = EmployeeRepository(EmployeeDatabase.getDatabase(application).employeeDao())

    fun insertEmployee(name: String) = viewModelScope.launch {
        val newEmployee = Employee(name = name)
        repository.insert(newEmployee)
    }
}
