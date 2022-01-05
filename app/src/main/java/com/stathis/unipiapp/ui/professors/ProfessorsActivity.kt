package com.stathis.unipiapp.ui.professors

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.ProfessorCallback
import com.stathis.unipiapp.databinding.ActivityProfessorsBinding
import com.stathis.unipiapp.models.Professor

class ProfessorsActivity : UnipiActivity<ActivityProfessorsBinding>(R.layout.activity_professors) {

    private lateinit var viewModel : ProfessorsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ProfessorsViewModel::class.java)
    }

    override fun startOps() {
        /*
            FIXME: Mrs. Virvou image is not loading correctly
         */

        supportActionBar?.title = resources.getString(R.string.professors)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.setVariable(BR.viewModel,viewModel)

        viewModel.observe(this,object : ProfessorCallback {
            override fun onProfessorTap(model: Professor) = openPopUpWindow(model)
        })
    }

    override fun stopOps() {
        viewModel.release(this)
    }

    private fun openPopUpWindow(professor : Professor) {
        MaterialAlertDialogBuilder(this).also {
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
            Toast.makeText(this,getString(R.string.no_clients_installed), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.professor_menu,menu)

        val item : MenuItem? = menu?.findItem(R.id.professor_search)
        val searchView = item?.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint = resources.getString(R.string.search_professor)

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean = false
            override fun onQueryTextChange(query: String?): Boolean {
                when(query.isNullOrEmpty()){
                    true -> viewModel.getProfessors()
                    false -> viewModel.filter(query)
                }

                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            viewModel.getProfessors()
            true
        }

        else -> false
    }
}