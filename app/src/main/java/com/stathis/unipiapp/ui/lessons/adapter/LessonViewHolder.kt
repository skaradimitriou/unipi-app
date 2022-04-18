package com.stathis.unipiapp.ui.lessons.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.databinding.HolderLessonItemBinding
import com.stathis.unipiapp.models.InfoModel
import com.stathis.unipiapp.models.Lesson
import com.stathis.unipiapp.models.LocalModel

class LessonViewHolder(val binding: ViewDataBinding) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is Lesson -> {
                binding.setVariable(BR.model, data)
                (binding as HolderLessonItemBinding).lessonCard.setOnClickListener {
                    data.isExpanded = !data.isExpanded

                    when (data.isExpanded) {
                        true -> {
                            binding.iconMore.animate().rotation(90f).start()
                            binding.lessonDesc.visibility = View.VISIBLE
                        }
                        false -> {
                            binding.iconMore.animate().rotation(0f).start()
                            binding.lessonDesc.visibility = View.GONE
                        }
                    }
                }
            }

            is InfoModel -> binding.setVariable(BR.model, data)
        }
    }
}