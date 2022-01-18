package com.stathis.unipiapp.ui.dashboard.lessons


import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.callbacks.EclassLessonCallback
import com.stathis.unipiapp.databinding.FragmentLessonsBinding
import com.stathis.unipiapp.ui.dashboard.lessons.model.EclassLesson
import com.stathis.unipiapp.ui.eclassAnnouncements.EclassAnnouncementsActivity

class MyLessonsFragment : UnipiFragment<FragmentLessonsBinding>(R.layout.fragment_lessons) {

    private lateinit var viewModel : MyLessonsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(MyLessonsViewModel::class.java)
    }

    override fun startOps() {
        binding.viewModel = viewModel

        viewModel.user.observe(this, Observer {
            binding.myLessonsGreeting.text = getString(R.string.eclass_user_greeting,it)
        })

        viewModel.observe(this,object : EclassLessonCallback{
            override fun onLessonTap(model: EclassLesson) {
                startActivity(Intent(requireContext(),EclassAnnouncementsActivity::class.java).also {
                    it.putExtra("LESSON",model)
                })
            }
        })
    }

    override fun stopOps() {
        viewModel.release(this)
    }
}