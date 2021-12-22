package com.stathis.unipiapp.ui.dashboard.professors

import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.databinding.FragmentProfessorsBinding


class ProfessorsFragment : UnipiFragment<FragmentProfessorsBinding>(R.layout.fragment_professors) {

    private lateinit var viewModel : ProfessorsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ProfessorsViewModel::class.java)
    }

    override fun startOps() {
        binding.name = resources.getString(R.string.menu_professors)
    }

    override fun stopOps() {
        //
    }
}