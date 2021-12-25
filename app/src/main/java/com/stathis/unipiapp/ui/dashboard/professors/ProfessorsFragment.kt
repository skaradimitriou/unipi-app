package com.stathis.unipiapp.ui.dashboard.professors

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
            override fun onProfessorTap(model: Professor) = openPopUpWindow(model)
        })
    }

    override fun stopOps() {
        viewModel.release(this)
    }

    private fun openPopUpWindow(professor : Professor) {
        MaterialAlertDialogBuilder(requireContext()).also {
            it.setTitle(getString(R.string.dialog_new_email))
            when(professor.gender){
                resources.getString(R.string.male) -> it.setMessage(getString(R.string.send_email_to_male_professor).format(professor.fullName))
                resources.getString(R.string.female) -> it.setMessage(getString(R.string.send_email_to_female_professor).format(professor.fullName))
            }

            it.setPositiveButton(getString(R.string.dialog_yes)) { dialog, which -> sendEmail(professor) }
            it.setNegativeButton(getString(R.string.dialog_cancel)) { dialog, which -> dialog.dismiss() }
        }.show()
    }

    private fun sendEmail(professor : Professor){
        val i = Intent(Intent.ACTION_SEND)
            .setType(getString(R.string.email_type))
            .putExtra(Intent.EXTRA_EMAIL, arrayOf(professor.email))

        try {
            startActivity(Intent.createChooser(i, getString(R.string.sending_email)))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(requireContext(),getString(R.string.no_clients_installed),Toast.LENGTH_SHORT).show()
        }
    }
}