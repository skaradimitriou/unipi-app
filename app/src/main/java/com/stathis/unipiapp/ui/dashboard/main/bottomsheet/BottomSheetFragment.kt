package com.stathis.unipiapp.ui.dashboard.main.bottomsheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.stathis.unipiapp.R
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.MainScreenBottomSheetBinding
import com.stathis.unipiapp.models.ShortCategory
import com.stathis.unipiapp.ui.about.AboutActivity
import com.stathis.unipiapp.ui.announcements.AnnouncementsActivity
import com.stathis.unipiapp.ui.contact.ContactActivity
import com.stathis.unipiapp.ui.dashboard.main.MainViewModel
import com.stathis.unipiapp.ui.dashboard.main.bottomsheet.adapter.ShortCategoriesAdapter
import com.stathis.unipiapp.ui.department.DepartmentActivity
import com.stathis.unipiapp.ui.professors.ProfessorsActivity
import com.stathis.unipiapp.ui.services.ServicesActivity
import com.stathis.unipiapp.ui.students.StudentsActivity
import com.stathis.unipiapp.ui.syllabus.SyllabusActivity

class BottomSheetFragment() : BottomSheetDialogFragment(), UnipiCallback {

    private lateinit var binding : MainScreenBottomSheetBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewRoot = LayoutInflater.from(requireContext()).inflate(R.layout.main_screen_bottom_sheet, container, false)
        binding = DataBindingUtil.bind(viewRoot)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        val adapter = ShortCategoriesAdapter(this)

        binding.bottomSheetRecycler.adapter = adapter

        val data = viewModel.getAllCategories()
        adapter.submitList(data)
    }

    override fun onItemTap(view: View) = when(view.tag){
        is ShortCategory -> {
            when((view.tag as ShortCategory).title){
                getString(R.string.menu_announcements) -> startActivity(Intent(requireContext(),AnnouncementsActivity::class.java))
                getString(R.string.department) -> startActivity(Intent(requireContext(),DepartmentActivity::class.java))
                getString(R.string.students) -> startActivity(Intent(requireContext(),StudentsActivity::class.java))
                getString(R.string.syllabus) -> startActivity(Intent(requireContext(),SyllabusActivity::class.java))
                getString(R.string.syllabus) -> startActivity(Intent(requireContext(),ProfessorsActivity::class.java))
                getString(R.string.contact) -> startActivity(Intent(requireContext(),ContactActivity::class.java))
                "Υπηρεσίες" -> startActivity(Intent(requireContext(),ServicesActivity::class.java))
                getString(R.string.about_app_title) -> startActivity(Intent(requireContext(),AboutActivity::class.java))
                else -> Unit
            }
        }
        else -> Unit
    }
}