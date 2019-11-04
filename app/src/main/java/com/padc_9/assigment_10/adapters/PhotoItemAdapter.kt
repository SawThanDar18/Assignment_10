package com.padc_9.assigment_10.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc_9.assigment_10.R
import com.padc_9.assigment_10.data.vos.PhotoVO
import com.padc_9.assigment_10.delegates.ItemClicked
import com.padc_9.assigment_10.viewHolders.PhotoItemViewHolder

class PhotoItemAdapter(private val delegate: ItemClicked): BaseAdapter<PhotoItemViewHolder, PhotoVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.photo_item_view, parent, false)
        return PhotoItemViewHolder(layout, delegate)
    }
}