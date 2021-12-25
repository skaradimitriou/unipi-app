package com.stathis.unipiapp.ui.dashboard.main

import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.callbacks.MainScreenCallback
import com.stathis.unipiapp.databinding.FragmentMainBinding
import com.stathis.unipiapp.models.UnipiItem


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
                "Item 1" -> goToDepartment()
                "Item 2" -> goToServices()
                "Item 3" -> goToContact()
                "Item 4" -> goToDepartment()
                else -> Unit
            }
        })
    }

    override fun stopOps() {}

    fun goToDepartment(){}

    fun goToServices(){}

    fun goToContact(){}
}