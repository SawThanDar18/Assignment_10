package com.padc_9.assigment_10.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.padc_9.assigment_10.R
import com.padc_9.assigment_10.adapters.PhotoItemAdapter
import com.padc_9.assigment_10.data.vos.PhotoVO
import com.padc_9.assigment_10.mvp.presenters.PhotoDetailPresenter
import com.padc_9.assigment_10.mvp.presenters.PhotoListPresenter
import com.padc_9.assigment_10.mvp.views.PhotoDetailView
import com.padc_9.assigment_10.mvp.views.PhotoListView
import kotlinx.android.synthetic.main.activity_photo_detail.*
import kotlinx.android.synthetic.main.activity_photo_detail.rootView
import kotlinx.android.synthetic.main.activity_photo_list.*
import kotlinx.android.synthetic.main.bottom_sheet.*

class PhotoDetailActivity: BaseAcitvity(), PhotoDetailView, PhotoListView {

    override fun displayPhotoDetail(photoVO: PhotoVO) {
        bindData(photoVO)
    }

    override fun displayPhotoList(photoList: List<PhotoVO>) {
        photoItemAdapter.setNewData(photoList.toMutableList())
    }

    override fun displayError(errorMessage: String) {
        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun navigateToDetail(photoId: String) {
        startActivity(newIntent(applicationContext, photoId))
    }

    override fun errorMessage(errorMessage: String) {
        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    private lateinit var photoDetailPresenter: PhotoDetailPresenter
    private lateinit var photoListPresenter: PhotoListPresenter
    private lateinit var photoItemAdapter: PhotoItemAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_photo_detail)

        photoDetailPresenter = PhotoDetailPresenter()
        photoDetailPresenter.initPresenter(this)

        photoListPresenter = PhotoListPresenter()
        photoListPresenter.initPresenter(this)

        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)

        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(@NonNull view: View, i: Int) {
                when (i) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        bottomSheetBehavior.setPeekHeight(300)
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(@NonNull view: View, v: Float) {

            }
        })

        photoItemAdapter = PhotoItemAdapter(photoListPresenter)
        with(rv_related_photos){
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = photoItemAdapter
        }

        val photoId = intent.getStringExtra(EXTRA_PHOTO_ID)
        photoDetailPresenter.onUiReady(this, photoId!!)
        photoListPresenter.onUiReady(this)

    }

    companion object {
        const val EXTRA_PHOTO_ID = "photo_id"

        fun newIntent(context: Context, photo_id: String): Intent {

            return Intent(context, PhotoDetailActivity::class.java).apply {
                putExtra(EXTRA_PHOTO_ID, photo_id)
            }
        }
    }

    fun bindData(data: PhotoVO){
        photo_detail_iv.load(data.photoUrlVO.regular)
        user_iv.load(data.userVO.profileImageVO!!.medium)
        user_tv.text = data.userVO.name
        user_account_tv.text = "@" + data.userVO.twitter_username
    }
}
