package com.reign.hackernewstest.ui.activities.main

import com.reign.hackernewstest.ui.base.BaseContract


class MainContract {

    interface View: BaseContract.View {
        fun init()

    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {


    }
}