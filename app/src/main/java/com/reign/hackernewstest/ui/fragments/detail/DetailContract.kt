package com.reign.hackernewstest.ui.fragments.detail

import com.reign.hackernewstest.ui.base.BaseContract

class DetailContract {
    interface View: BaseContract.View {
        fun init()
    }

    interface Presenter: BaseContract.Presenter<DetailContract.View> {

    }
}