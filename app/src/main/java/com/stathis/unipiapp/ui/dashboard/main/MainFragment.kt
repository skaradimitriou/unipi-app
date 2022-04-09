package com.stathis.unipiapp.ui.dashboard.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.callbacks.MainScreenCallback
import com.stathis.unipiapp.databinding.FragmentMainBinding
import com.stathis.unipiapp.models.UnipiItem
import com.stathis.unipiapp.ui.announcements.AnnouncementsActivity
import com.stathis.unipiapp.ui.dashboard.DashboardViewModel
import com.stathis.unipiapp.ui.department.DepartmentActivity
import com.stathis.unipiapp.ui.professors.ProfessorsActivity
import com.stathis.unipiapp.ui.students.StudentsActivity
import timber.log.Timber


class MainFragment : UnipiFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private lateinit var viewModel : MainViewModel
    private lateinit var sharedViewModel : DashboardViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)
    }

    override fun startOps() {
        activity?.title = "Αρχική"

        binding.setVariable(BR.viewModel,viewModel)

        sharedViewModel.activeUser.observe(viewLifecycleOwner){
            it?.let {
                binding.unipiHeaderBg.apply {
                    val fullname = "${it.student?.info?.firstName} ${it.student?.info?.lastName}"
                    this.unipiDesc.text = fullname
                }
            }
        }

        viewModel.addListener(object : MainScreenCallback {
            override fun onItemTap(model: UnipiItem) = when(model.title){
                resources.getString(R.string.announcements) -> goToAnnouncements()
                resources.getString(R.string.students) -> goToStudents()
                resources.getString(R.string.department) -> goToDepartment()
                resources.getString(R.string.professors) -> goToProfessors()
                else -> Unit
            }
        })
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