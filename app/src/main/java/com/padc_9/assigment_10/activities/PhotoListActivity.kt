package com.padc_9.assigment_10.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padc_9.assigment_10.R
import com.padc_9.assigment_10.adapters.PhotoItemAdapter
import com.padc_9.assigment_10.data.models.PhotoListModel
import com.padc_9.assigment_10.data.models.PhotoListModelImpl
import com.padc_9.assigment_10.data.vos.PhotoVO
import com.padc_9.assigment_10.mvp.presenters.PhotoListPresenter
import com.padc_9.assigment_10.mvp.views.PhotoListView
import kotlinx.android.synthetic.main.activity_photo_list.*

class PhotoListActivity : BaseAcitvity(), PhotoListView {

    override fun displayPhotoList(photoList: List<PhotoVO>) {
        photoItemAdapter.setNewData(photoList.toMutableList())
    }

    override fun displayError(errorMessage: String) {
        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun navigateToDetail(photoId: String) {
        startActivity(PhotoDetailActivity.newIntent(applicationContext, photoId))
    }

    private lateinit var photoItemAdapter: PhotoItemAdapter
    private lateinit var photoListPresenter: PhotoListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_photo_list)

        photoListPresenter = ViewModelProviders.of(this).get(PhotoListPresenter::class.java)
        photoListPresenter.initPresenter(this)

        photoItemAdapter = PhotoItemAdapter(photoListPresenter)

        with(rv_photos){
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = photoItemAdapter
        }

        search_et.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val search_keyword = search_et.text.toString()
                return@OnEditorActionListener true
            }
            false
        })

        search_et.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                photoListPresenter.onUiReady(this)
            }
            false
        }

        search_et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                searchByKeyword(s.toString())
            }
        })

        photoListPresenter.onUiReady(this)
    }

    fun searchByKeyword(keyword: String){
        val model: PhotoListModel = PhotoListModelImpl
        photoItemAdapter.setNewData(model.getSearchPhoto(keyword) as MutableList<PhotoVO>)
        rv_photos.adapter = photoItemAdapter
    }

}
