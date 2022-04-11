package com.stathis.unipiapp.util

import android.content.SharedPreferences
import android.text.Html
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText
import com.stathis.unipiapp.models.Result

fun String.toNonHtmlText(): String {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this,Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}

fun TextInputEditText.clearInput() {
    this.setText("")
    this.clearFocus()
}

fun <T>MutableLiveData<Result<T>>.setData(data : T?){
    data?.let { this.postValue(Result.Success(it)) }
}

fun <T>MutableLiveData<Result<T>>.setError(data : String?){
    data?.let { this.postValue(Result.Error(it)) }
}

fun SharedPreferences.save(values: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.values()
    editor.apply()
}