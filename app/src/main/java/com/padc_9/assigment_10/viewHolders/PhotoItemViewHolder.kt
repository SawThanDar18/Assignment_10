package com.padc_9.assigment_10.viewHolders

import android.view.View
import coil.api.load
import com.padc_9.assigment_10.data.vos.PhotoVO
import com.padc_9.assigment_10.delegates.ItemClicked
import kotlinx.android.synthetic.main.photo_item_view.view.*

class PhotoItemViewHolder(itemView: View, private val delegate: ItemClicked) : BaseViewHolder<PhotoVO>(itemView) {

    init {
        itemView.photo_iv.setOnClickListener {
            data?.id?.let { id ->
                delegate.onItemClicked(id)
            }
        }
    }
    override fun bindData(data: PhotoVO) {
        itemView.photo_iv.load(data.photoUrlVO.small)
    }
}