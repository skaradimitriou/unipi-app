package com.stathis.unipiapp.ui.webview

import android.view.MenuItem
import android.webkit.WebSettings
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivitySyllabusBinding
import com.stathis.unipiapp.databinding.ActivityWebviewBinding
import com.stathis.unipiapp.models.Announcement
import com.stathis.unipiapp.network.SSLWebViewClient
import com.stathis.unipiapp.util.BASE_URL

class WebviewActivity : UnipiActivity<ActivityWebviewBinding>(R.layout.activity_webview) {

    override fun init() {}

    override fun startOps() {
        val model = intent.getParcelableExtra<Announcement>(resources.getString(R.string.model))

        model?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = model.title

            val newUrl = when (model.url.startsWith(BASE_URL)) {
                true -> model.url
                false -> BASE_URL.plus(model.url)
            }

            binding.webviewWindow.loadUrl(newUrl)

            binding.webviewWindow.webViewClient = SSLWebViewClient

            val webSettings: WebSettings = binding.webviewWindow.settings
            webSettings.javaScriptEnabled = true
            webSettings.domStorageEnabled = true
            webSettings.builtInZoomControls = true
        }
    }

    override fun stopOps() {}

    override fun onBackPressed() {
        when {
            binding.webviewWindow.canGoBack() -> binding.webviewWindow.goBack()
            else -> super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }
}