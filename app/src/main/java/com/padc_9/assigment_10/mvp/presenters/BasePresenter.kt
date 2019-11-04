package com.padc_9.assigment_10.mvp.presenters

import androidx.lifecycle.ViewModel
import com.padc_9.assigment_10.mvp.views.BaseView

abstract class BasePresenter<T: BaseView> : ViewModel() {

    protected lateinit var mView: T

    open fun initPresenter(view: T){
        this.mView  = view
    }
}