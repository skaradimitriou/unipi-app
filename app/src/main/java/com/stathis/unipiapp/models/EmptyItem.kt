package com.stathis.unipiapp.models

class EmptyItem() : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is EmptyItem -> true
        else -> false
    }
}