package com.stathis.unipiapp.ui.eclassAnnouncements

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.EclassAnnouncementsCallback
import com.stathis.unipiapp.databinding.ActivityEclassAnnouncementsBinding
import com.stathis.unipiapp.databinding.EclassAnnouncementBottomSheetBinding
import com.stathis.unipiapp.databinding.ProfessorsBottomSheetBinding
import com.stathis.unipiapp.ui.dashboard.lessons.model.EclassLesson
import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncement
import com.stathis.unipiapp.util.toNonHtmlText
import org.jsoup.Jsoup

class EclassAnnouncementsActivity :
    UnipiActivity<ActivityEclassAnnouncementsBinding>(R.layout.activity_eclass_announcements) {

    private lateinit var viewModel: EclassAnnouncementsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(EclassAnnouncementsViewModel::class.java)
    }

    override fun startOps() {
        binding.viewModel = viewModel

        val model = intent.getParcelableExtra<EclassLesson>("LESSON")
        model?.let {
            supportActionBar?.title = resources.getString(R.string.lesson_announcements)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            viewModel.getData(it.code)
        }

        observe()

        binding.swipe2refreshLayout.setOnRefreshListener {
            model?.let { viewModel.getData(it.code) }
        }
    }

    override fun stopOps() = viewModel.release(this)

    private fun observe() {
        viewModel.observe(this, object : EclassAnnouncementsCallback {
            override fun onEclassAnnouncementTap(model: EclassAnnouncement) = openOptions(model)
        })

        viewModel.data.observe(this) {
            when (!it.itemList.isNullOrEmpty()) {
                true -> binding.swipe2refreshLayout.isRefreshing = false
            }
        }

        viewModel.error.observe(this) {
            when (it) {
                true -> Snackbar.make(
                    binding.eclassAnnouncementsParent,
                    getString(R.string.snackbar_error),
                    Snackbar.LENGTH_LONG
                ).show()
                false -> Unit
            }
        }
    }

    private fun openOptions(model: EclassAnnouncement) {
        val dBinding = EclassAnnouncementBottomSheetBinding.inflate(LayoutInflater.from(this))
        val dialog = BottomSheetDialog(this).also {
            it.setContentView(dBinding.root)
            dBinding.model = model
        }
        dialog.show()

        dBinding.openAnnouncement.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(model.link) })
        }

        dBinding.copyAnnouncement.setOnClickListener {
            val clipboard: ClipboardManager =getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("", model.description.toNonHtmlText())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this,getString(R.string.announcement_copied), Toast.LENGTH_LONG).show()
        }

        dBinding.shareAnnouncement.setOnClickListener {
            val intent = Intent().also {
                it.action = Intent.ACTION_SEND
                it.type = "text/plain"
                it.putExtra(Intent.EXTRA_TEXT, model.description.toNonHtmlText())
            }
            startActivity(Intent.createChooser(intent, "Share with:"))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}