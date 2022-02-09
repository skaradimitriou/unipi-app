package com.stathis.unipiapp.ui.contact

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.ContactCallback
import com.stathis.unipiapp.databinding.ActivityContactBinding
import com.stathis.unipiapp.databinding.ContactScreenBottomSheetBinding
import com.stathis.unipiapp.models.ContactItem

class ContactActivity : UnipiActivity<ActivityContactBinding>(R.layout.activity_contact) {

    private lateinit var viewModel: ContactViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.contact)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewModel = viewModel

        viewModel.observe(this, object : ContactCallback {
            override fun onItemTap(model: ContactItem) =  showContactOptions(model)
        })
    }

    override fun stopOps() {
        viewModel.release(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }

    private fun showContactOptions(model: ContactItem) {
        val binding = ContactScreenBottomSheetBinding.inflate(LayoutInflater.from(this))
        BottomSheetDialog(this).also {
            it.setContentView(binding.root)
        }.show()

        binding.bottomSheetEmail.setOnClickListener { sendMail(model.email) }
        binding.bottomSheetTelephone.setOnClickListener { showCallOptions(model) }
    }

    private fun showCallOptions(model: ContactItem) {
        val items = model.telephone.split(",").map { it.trim() }.toTypedArray()

        MaterialAlertDialogBuilder(this@ContactActivity)
            .setTitle(resources.getString(R.string.choose_contact))
            .setItems(items) { dialog, which ->
                val selectedTelephone = items[which]
                startActivity(Intent(Intent.ACTION_DIAL,Uri.parse("tel:$selectedTelephone")))
            }
            .show()
    }

    private fun sendMail(email : String) {
        val i = Intent(Intent.ACTION_SEND)
            .setType(resources.getString(R.string.email_type))
            .putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(email))

        try {
            startActivity(Intent.createChooser(i, resources.getString(R.string.sending_email)))
        } catch (ex: ActivityNotFoundException) {}
    }
}