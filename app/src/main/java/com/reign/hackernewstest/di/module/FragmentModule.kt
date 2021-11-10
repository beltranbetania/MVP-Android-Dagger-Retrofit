package com.reign.hackernewstest.di.module

import com.reign.hackernewstest.api.ApiServiceInterface
import com.reign.hackernewstest.ui.fragments.articlesList.ListArticlesContract
import com.reign.hackernewstest.ui.fragments.articlesList.ListArticlesPresenter
import com.reign.hackernewstest.ui.fragments.detail.DetailContract
import com.reign.hackernewstest.ui.fragments.detail.DetailPresenter


import dagger.Module
import dagger.Provides


@Module
class FragmentModule {

    @Provides
    fun provideListArticlesPresenter(): ListArticlesContract.Presenter {
        return ListArticlesPresenter()
    }

    @Provides
    fun provideDetailPresenter(): DetailContract.Presenter {
        return DetailPresenter()
    }


    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}