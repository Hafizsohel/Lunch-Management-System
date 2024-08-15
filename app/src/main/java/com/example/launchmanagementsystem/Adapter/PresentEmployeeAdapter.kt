package com.example.launchmanagementsystem.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.launchmanagementsystem.Model.Employee
import com.example.launchmanagementsystem.R

class PresentEmployeeAdapter : RecyclerView.Adapter<PresentEmployeeAdapter.PresentEmployeeViewHolder>() {
    private var presentEmployees = mutableListOf<Employee>()
    inner class PresentEmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val presentEmployeeName: TextView = itemView.findViewById(R.id.employeeName)
        val serialNoText: TextView = itemView.findViewById(R.id.serialNoText)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresentEmployeeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.present_name_list, parent, false)
        return PresentEmployeeViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: PresentEmployeeViewHolder, position: Int) {
        val currentEmployee = presentEmployees[position]
        holder.serialNoText.text = "${position + 1}."
        holder.presentEmployeeName.text = currentEmployee.name
    }
    override fun getItemCount(): Int = presentEmployees.size
    fun setEmployees(employees: List<Employee>) {
        this.presentEmployees = employees.toMutableList()
        notifyDataSetChanged()
    }
    fun getEmployees(): List<Employee> {
        return presentEmployees
    }
}
