package com.stathis.unipiapp.ui.services

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.ServicesCallback
import com.stathis.unipiapp.databinding.ActivityServicesBinding
import com.stathis.unipiapp.models.UnipiService
import com.stathis.unipiapp.util.BASE_URL

class ServicesActivity : UnipiActivity<ActivityServicesBinding>(R.layout.activity_services) {

    private lateinit var viewModel : ServicesViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ServicesViewModel::class.java)
    }

    override fun startOps() {
        binding.setVariable(BR.viewModel,viewModel)

        viewModel.observe(this, object : ServicesCallback{
            override fun onServiceTap(model: UnipiService) {
                startActivity(Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(BASE_URL +model.url) })
            }
        })

        viewModel.error.observe(this,Observer{
            when(it){
                true -> {} //handle cases
                false -> Unit
            }
        })
    }

    override fun stopOps() {
        viewModel.release(this)
    }
}