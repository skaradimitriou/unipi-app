package com.stathis.unipiapp.models

data class ContactItem(

    val title: String,
    val address: String,
    val telephone: String,
    val email: String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is ContactItem -> obj.title == title && obj.address == address && obj.telephone == telephone && obj.email == email
        else -> false
    }
}