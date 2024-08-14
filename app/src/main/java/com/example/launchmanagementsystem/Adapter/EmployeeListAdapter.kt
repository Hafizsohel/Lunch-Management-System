package com.example.launchmanagementsystem.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.launchmanagementsystem.Model.Employee
import com.example.launchmanagementsystem.R
import com.example.launchmanagementsystem.ViewModel.EmployeeViewModel

private const val TAG = "EmployeeAdapter"

class EmployeeListAdapter(
    private val employeeViewModel: EmployeeViewModel
) : RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>() {
    private var employees = mutableListOf<Employee>()
    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.employeeName)
        val serialNoText: TextView = itemView.findViewById(R.id.serialNoText)
        val checkPresent: CheckBox = itemView.findViewById(R.id.checkPresent)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_item_view, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentEmployee = employees[position]
        holder.serialNoText.text = "${position + 1}."
        holder.employeeName.text = currentEmployee.name
        holder.checkPresent.isChecked = currentEmployee.isPresent

        holder.checkPresent.setOnClickListener {
            val isChecked = holder.checkPresent.isChecked
            val updatedEmployee = currentEmployee.copy(isPresent = isChecked)
            employeeViewModel.addEmployee(updatedEmployee)
             Log.d(TAG, "onBindViewHolder: Employee ${currentEmployee.name} is present: $isChecked")
        }
    }
    override fun getItemCount(): Int = employees.size

    fun setEmployees(employees: List<Employee>) {
        this.employees = employees.toMutableList()
        notifyDataSetChanged()
    }
}
