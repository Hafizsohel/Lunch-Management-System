package com.example.launchmanagementsystem.Fragments

import com.example.launchmanagementsystem.Adapter.PresentEmployeeAdapter
import com.example.launchmanagementsystem.ViewModel.EmployeeViewModel
import com.example.launchmanagementsystem.databinding.FragmentEmployeePresentBinding
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val TAG = "EmployeeStatusFragment"
class EmployeePresentFragment : Fragment() {

    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var presentAdapter: PresentEmployeeAdapter
    private lateinit var binding: FragmentEmployeePresentBinding

    private var selectedDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeePresentBinding.inflate(layoutInflater)

        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        presentAdapter = PresentEmployeeAdapter()


        // Display the current date in the TextView
        val currentDate = getCurrentDateString()
        Log.d(TAG, "Current date: $currentDate")

        // Set the current date to the TextView
        binding.scrumDate.text = "Scrum Date: $currentDate"

        Log.d(TAG, "Selected date in Scrum Date: $selectedDate")


        binding.presentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = presentAdapter
        }


        // Observe data from the ViewModel
        employeeViewModel.presentEmployees.observe(viewLifecycleOwner) { employees ->
            Log.d(TAG, "Present employees: $employees")
            presentAdapter.setEmployees(employees)
            updateRecyclerViewVisibility()
        }


        binding.btnShare.setOnClickListener {
            shareEmployeeStatus()
        }
        return binding.root
    }

    private fun updateRecyclerViewVisibility() {
        val hasPresentEmployees = presentAdapter.itemCount > 0

        if (hasPresentEmployees) {
            binding.presentRecyclerView.visibility = View.VISIBLE
        } else {
            binding.presentRecyclerView.visibility = View.GONE
        }

    }
    private fun shareEmployeeStatus() {
        val presentEmployees = presentAdapter.getEmployees()

        if (presentEmployees.isEmpty()) {
            Toast.makeText(context, "No employee data to share", Toast.LENGTH_SHORT).show()
            return
        }

        val presentNames = presentEmployees.joinToString("\n") { it.name }

        val shareText = StringBuilder().apply {
            if(getCurrentDateString().isNotEmpty()){
                append("**Scrum Date:**\n${getCurrentDateString()}\n\n")
            }
            if (presentNames.isNotEmpty()) {
                append("**Present Employees:**\n$presentNames\n\n")
            }
        }.toString()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share via")
        startActivity(shareIntent)
    }
    private fun getCurrentDateString(): String {
        val myFormat = "d-MMM-yy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(Calendar.getInstance().time)
    }
}
