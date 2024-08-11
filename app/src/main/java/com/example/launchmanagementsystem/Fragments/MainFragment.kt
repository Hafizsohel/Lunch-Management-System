package com.example.launchmanagementsystem.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.launchmanagementsystem.ViewModel.EmployeeViewModel
import com.example.launchmanagementsystem.databinding.FragmentMainBinding

private const val TAG = "MainFragment"
class MainFragment : Fragment() {
    private lateinit var binding:FragmentMainBinding
    private lateinit var employeeViewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        binding.fabBtn.setOnClickListener {
            val dialogFragment = CustomDialogFragment()
            dialogFragment.show(parentFragmentManager, "CustomDialogFragment")
        }

        binding.btnToday.setOnClickListener {

        }
        return binding.root
    }
}