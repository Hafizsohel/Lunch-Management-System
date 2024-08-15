package com.example.launchmanagementsystem.Fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.launchmanagementsystem.Adapter.EmployeeListAdapter
import com.example.launchmanagementsystem.Adapter.PresentEmployeeAdapter
import com.example.launchmanagementsystem.R
import com.example.launchmanagementsystem.ViewModel.EmployeeViewModel
import com.example.launchmanagementsystem.databinding.FragmentEmployeeListBinding

class EmployeeListFragment : Fragment() {

    private lateinit var binding: FragmentEmployeeListBinding
    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var employeeListAdapter: EmployeeListAdapter
    private lateinit var presentEmployeeAdapter: PresentEmployeeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeeListBinding.inflate(layoutInflater)

        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        employeeListAdapter = EmployeeListAdapter(employeeViewModel)
        presentEmployeeAdapter = PresentEmployeeAdapter()

        binding.employeeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.employeeRecyclerView.adapter = employeeListAdapter

        binding.recyclerViewEmployeePresent.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewEmployeePresent.adapter = presentEmployeeAdapter

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        employeeViewModel.allEmployees.observe(viewLifecycleOwner) { employees ->
            employees?.let {
                employeeListAdapter.setEmployees(it)
                binding.titleEmployeeNames.text = "Employee Names List: ( Total = ${it.size} )"
            }
        }

        employeeViewModel.presentEmployees.observe(viewLifecycleOwner) { employees ->
            employees?.let {
                presentEmployeeAdapter.setEmployees(it)
                binding.titleEmployeePresent.text = "Present Employee List: ( Total = ${it.size} )"
            }
        }

        binding.okButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.FrameLayoutID, MainFragment())
                .commit()
        }
        return binding.root
    }
}
