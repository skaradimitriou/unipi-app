package com.stathis.unipiapp.ui.dashboard.syllabus

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.callbacks.SemesterCallback
import com.stathis.unipiapp.databinding.FragmentSyllabusBinding
import com.stathis.unipiapp.models.Semester
import com.stathis.unipiapp.ui.lessons.LessonsActivity
import timber.log.Timber


class SyllabusFragment : UnipiFragment<FragmentSyllabusBinding>(R.layout.fragment_syllabus) {

    private lateinit var viewModel : SyllabusViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(SyllabusViewModel::class.java)
    }

    override fun startOps() {
        activity?.title = getString(R.string.lessons_mppl_title)

        binding.viewModel = viewModel

        viewModel.observe(viewLifecycleOwner, object : SemesterCallback {
            override fun onSemesterTap(model: Semester) {
                startActivity(Intent(requireContext(), LessonsActivity::class.java).also {
                    it.putExtra(getString(R.string.model),model)
                })
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            when(it){
                true -> {} //FIXME: Throw some kind of notification to user
                false -> Unit
            }
        })
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }

}