package com.stathis.unipiapp.ui.lessons

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.unipiapp.R
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityLessonsBinding
import com.stathis.unipiapp.models.Professor
import com.stathis.unipiapp.models.Semester

class LessonsActivity : UnipiActivity<ActivityLessonsBinding>(R.layout.activity_lessons) {

    private lateinit var viewModel : LessonsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(LessonsViewModel::class.java)
    }

    override fun startOps() {
        val semester = intent.getParcelableExtra<Semester>("MODEL")
        semester?.let { viewModel.bindData(semester.lessons) }

        supportActionBar?.title = semester?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewModel = viewModel
    }

    override fun stopOps() {}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lessons_menu, menu)
        val item : MenuItem? = menu?.findItem(R.id.lesson_info)

        item?.setOnMenuItemClickListener {
            openPopUpWindow()
            true
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun openPopUpWindow() {
        MaterialAlertDialogBuilder(this).also {
            it.setTitle(getString(R.string.info_title))
            it.setMessage(getString(R.string.info_body))
        }.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }
}