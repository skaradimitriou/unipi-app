package com.stathis.unipiapp.ui.department.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.*
import com.stathis.unipiapp.models.CarouselParent
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.ui.department.model.HorizontalDepartmentItem
import com.stathis.unipiapp.ui.department.model.VerticalDepartmentItem

class DepartmentAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, DepartmentViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when(viewType){
            R.layout.holder_viewpager_carousel_item -> HolderViewpagerCarouselItemBinding.inflate(inflater,parent,false)
            R.layout.holder_dept_horizontal_nested_item -> HolderDeptHorizontalNestedItemBinding.inflate(inflater,parent,false)
            R.layout.holder_dept_vertical_nested_item -> HolderDeptVerticalNestedItemBinding.inflate(inflater,parent,false)
            else -> HolderEmptyLayoutBinding.inflate(inflater,parent,false)
        }
        return DepartmentViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is CarouselParent -> R.layout.holder_viewpager_carousel_item
        is HorizontalDepartmentItem -> R.layout.holder_dept_horizontal_nested_item
        is VerticalDepartmentItem -> R.layout.holder_dept_vertical_nested_item
        else -> R.layout.holder_empty_view
    }
}