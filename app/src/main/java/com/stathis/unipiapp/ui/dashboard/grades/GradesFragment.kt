package com.stathis.unipiapp.ui.dashboard.grades

import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.databinding.FragmentGradesBinding


class GradesFragment : UnipiFragment<FragmentGradesBinding>(R.layout.fragment_grades) {

    private lateinit var viewModel : GradesViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(GradesViewModel::class.java)
    }

    override fun startOps() {
        activity?.title = getString(R.string.grade_screen_header)

        binding.viewModel = viewModel

        binding.model = viewModel.getData()
    }

    override fun stopOps() {}
}