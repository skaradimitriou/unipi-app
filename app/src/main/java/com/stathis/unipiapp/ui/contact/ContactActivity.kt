package com.stathis.unipiapp.ui.contact

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.ContactCallback
import com.stathis.unipiapp.databinding.ActivityContactBinding
import com.stathis.unipiapp.models.ContactItem

class ContactActivity : UnipiActivity<ActivityContactBinding>(R.layout.activity_contact) {

    private lateinit var viewModel: ContactViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.contact)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.adapter = viewModel.adapter

        viewModel.observe(this, object : ContactCallback{
            override fun onItemTap(model: ContactItem) {
                val items = model.telephone.split(",").map { it.trim() }.toTypedArray()
                /*
                FIXME: Bottom dialog fragment with 2 options:
                1. Send email
                2. Open dialog to select phone & prompt to call screen
                 */

                MaterialAlertDialogBuilder(this@ContactActivity)
                    .setTitle("Επιλογή Τηλεφώνου")
                    .setItems(items) { dialog, which ->
                        val selectedTelephone = items[which]
                        Log.d("",selectedTelephone)
                    }
                    .show()
            }
        })
    }

    override fun stopOps() {
        viewModel.release(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }
}