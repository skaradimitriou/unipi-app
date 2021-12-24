package com.stathis.unipiapp.ui.webview

import android.webkit.WebSettings
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivitySyllabusBinding

class WebviewActivity : UnipiActivity<ActivitySyllabusBinding>(R.layout.activity_syllabus) {

    override fun init() {}

    override fun startOps() {
        val url = intent.getStringExtra(resources.getString(R.string.url_tag))
        url?.let { binding.webviewWindow.loadUrl(it) }
        val webSettings: WebSettings = binding.webviewWindow.settings
        webSettings.javaScriptEnabled = true
    }

    override fun stopOps() {}

    override fun onBackPressed() {
        when {
            binding.webviewWindow.canGoBack() -> binding.webviewWindow.goBack()
            else -> super.onBackPressed()
        }
    }
}