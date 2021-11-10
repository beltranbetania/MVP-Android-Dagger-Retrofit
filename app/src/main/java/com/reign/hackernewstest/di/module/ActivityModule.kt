package com.reign.hackernewstest.di.module

import android.app.Activity
import com.reign.hackernewstest.ui.activities.main.MainContract
import com.reign.hackernewstest.ui.activities.main.MainPresenter


import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }


}