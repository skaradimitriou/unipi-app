package com.stathis.unipiapp.ui.dashboard.grades

import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.databinding.FragmentGradesBinding
import com.stathis.unipiapp.ui.dashboard.DashboardViewModel
import com.stathis.unipiapp.ui.dashboard.grades.adapter.GradesAdapter


class GradesFragment : UnipiFragment<FragmentGradesBinding>(R.layout.fragment_grades) {

    private lateinit var sharedViewModel: DashboardViewModel
    val adapter = GradesAdapter()

    override fun init() {
        sharedViewModel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)
    }

    override fun startOps() {
        activity?.title = getString(R.string.grade_screen_header)

        binding.gradesRecycler.adapter = adapter

        sharedViewModel.activeUser.observe(viewLifecycleOwner) { user ->
            user?.let {
                user.student?.grades?.let {
                    binding.model = it
                    adapter.submitList(it.semesters)
                }
            }
        }
    }

    override fun stopOps() {}
}