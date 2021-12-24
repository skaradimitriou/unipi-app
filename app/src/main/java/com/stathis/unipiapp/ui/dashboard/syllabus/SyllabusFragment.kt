package com.stathis.unipiapp.ui.dashboard.syllabus

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.callbacks.SemesterCallback
import com.stathis.unipiapp.databinding.FragmentSyllabusBinding
import com.stathis.unipiapp.models.Semester
import com.stathis.unipiapp.ui.lessons.LessonsActivity


class SyllabusFragment : UnipiFragment<FragmentSyllabusBinding>(R.layout.fragment_syllabus) {

    private lateinit var viewModel: SyllabusViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(SyllabusViewModel::class.java)
    }

    override fun startOps() {
        activity?.title = resources.getString(R.string.syllabus)

        binding.setVariable(BR.adapter, viewModel.adapter)

        viewModel.observe(this, object : SemesterCallback{
            override fun onSemesterTap(model: Semester) {
                startActivity(Intent(requireActivity(), LessonsActivity::class.java).also {
                    it.putExtra("MODEL",model)
                })
            }
        })
    }

    override fun stopOps() {
        viewModel.release(this)
    }
}