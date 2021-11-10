package com.reign.hackernewstest.di.module

import android.app.Application
import com.reign.hackernewstest.BaseApp
import com.reign.hackernewstest.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}