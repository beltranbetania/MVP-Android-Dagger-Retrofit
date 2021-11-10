package com.reign.hackernewstest.di.component

import com.reign.hackernewstest.BaseApp
import com.reign.hackernewstest.di.module.ApplicationModule
import dagger.Component


@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}