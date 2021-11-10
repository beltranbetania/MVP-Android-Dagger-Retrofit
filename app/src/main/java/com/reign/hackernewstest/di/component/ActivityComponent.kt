package com.reign.hackernewstest.di.component

import com.reign.hackernewstest.di.module.ActivityModule
import com.reign.hackernewstest.ui.activities.main.MainActivity
import dagger.Component


@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)



}