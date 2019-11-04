package com.padc_9.assigment_10.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var data: T? = null
        set(value) {
            field = value

            value?.let {
                bindData(value)
            }
        }

    protected abstract fun bindData(data: T)
}