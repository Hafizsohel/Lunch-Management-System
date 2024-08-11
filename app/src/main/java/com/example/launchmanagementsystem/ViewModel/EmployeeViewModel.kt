package com.example.launchmanagementsystem.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.launchmanagementsystem.Model.Employee
import com.example.launchmanagementsystem.Repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "EmployeeViewModel"
class EmployeeViewModel(private val repository: EmployeeRepository) : ViewModel() {

    fun addEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(employee)
        }
    }
}
