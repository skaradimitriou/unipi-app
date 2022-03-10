package com.stathis.unipiapp.ui.professors

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.ProfessorCallback
import com.stathis.unipiapp.databinding.ActivityProfessorsBinding
import com.stathis.unipiapp.databinding.ProfessorsBottomSheetBinding
import com.stathis.unipiapp.models.Professor
import android.content.*

class ProfessorsActivity : UnipiActivity<ActivityProfessorsBinding>(R.layout.activity_professors) {

    private lateinit var viewModel : ProfessorsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ProfessorsViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.professors)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewModel = viewModel

        viewModel.observe(this,object : ProfessorCallback {
            override fun onProfessorTap(model: Professor) = openBottomsheet(model)
        })
    }

    override fun stopOps() {
        viewModel.release(this)
    }

    private fun openBottomsheet(model : Professor){
        val dBinding = ProfessorsBottomSheetBinding.inflate(LayoutInflater.from(this))
        val dialog = BottomSheetDialog(this).also {
            it.setContentView(dBinding.root)
            dBinding.model = model
        }
        dialog.show()

        dBinding.bottomSheetEmail.setOnClickListener {
            sendEmail(model)
        }

        dBinding.copyProfessorEmail.setOnClickListener {
            copyText(model.email)
            Toast.makeText(this@ProfessorsActivity,getString(R.string.copied),Toast.LENGTH_LONG).show()
        }

        dBinding.shareProfessorData.setOnClickListener {
            val intent = Intent().also {
                it.action = Intent.ACTION_SEND
                it.type = "text/plain"
                it.putExtra(Intent.EXTRA_TEXT, "${model.fullName}, E-mail: ${model.email}")
            }
            startActivity(Intent.createChooser(intent, "Share with:"))
        }
    }

    private fun copyText(professorEmail : String){
        val clipboard: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("", professorEmail)
        clipboard.setPrimaryClip(clip)
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