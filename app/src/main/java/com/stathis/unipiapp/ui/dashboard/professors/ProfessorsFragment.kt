package com.stathis.unipiapp.ui.dashboard.professors

import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.callbacks.ProfessorCallback
import com.stathis.unipiapp.databinding.FragmentProfessorsBinding
import com.stathis.unipiapp.models.Professor


class ProfessorsFragment : UnipiFragment<FragmentProfessorsBinding>(R.layout.fragment_professors) {

    private lateinit var viewModel : ProfessorsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ProfessorsViewModel::class.java)
    }

    override fun startOps() {
        activity?.title = resources.getString(R.string.menu_professors)

        binding.setVariable(BR.adapter,viewModel.adapter)

        viewModel.observe(this,object : ProfessorCallback{
            override fun onProfessorTap(model: Professor) {
                //
            }
        })
    }

    override fun stopOps() {
        viewModel.release(this)
    }
}