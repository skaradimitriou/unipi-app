package com.stathis.unipiapp.util

import android.text.Html
import com.google.android.material.textfield.TextInputEditText

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