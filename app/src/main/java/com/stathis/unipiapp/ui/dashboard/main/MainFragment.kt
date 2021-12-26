package com.stathis.unipiapp.ui.dashboard.main

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.callbacks.MainScreenCallback
import com.stathis.unipiapp.databinding.FragmentMainBinding
import com.stathis.unipiapp.models.UnipiItem
import com.stathis.unipiapp.ui.announcements.AnnouncementsActivity
import com.stathis.unipiapp.ui.department.DepartmentActivity
import com.stathis.unipiapp.ui.professors.ProfessorsActivity
import com.stathis.unipiapp.ui.services.ServicesActivity
import com.stathis.unipiapp.ui.students.StudentsActivity


class MainFragment : UnipiFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private lateinit var viewModel : MainViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun startOps() {
        activity?.title = resources.getString(R.string.main_screen)

        binding.setVariable(BR.viewModel,viewModel)

        viewModel.addListener(object : MainScreenCallback {
            override fun onItemTap(model: UnipiItem) = when(model.title){
                resources.getString(R.string.menu_announcements) -> goToAnnouncements()
                resources.getString(R.string.students) -> goToStudents()
                resources.getString(R.string.department) -> goToDepartment()
                resources.getString(R.string.professors) -> goToProfessors()
                else -> Unit
            }
        })

        binding.categoriesAll.setOnClickListener {
            //open all categories
            Log.d("","")
        }
    }

    override fun stopOps() {}

    fun goToAnnouncements(){
        startActivity(Intent(requireContext(),AnnouncementsActivity::class.java))
    }

    fun goToDepartment(){
        startActivity(Intent(requireContext(),DepartmentActivity::class.java))
    }

    fun goToStudents(){
        startActivity(Intent(requireContext(),StudentsActivity::class.java))
    }

    fun goToProfessors(){
        startActivity(Intent(requireContext(),ProfessorsActivity::class.java))
    }
}