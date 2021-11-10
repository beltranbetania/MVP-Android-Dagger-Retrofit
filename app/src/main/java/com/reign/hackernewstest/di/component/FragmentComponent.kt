package com.reign.hackernewstest.di.component

import com.reign.hackernewstest.di.module.FragmentModule
import com.reign.hackernewstest.ui.fragments.articlesList.ListArticlesFragment
import com.reign.hackernewstest.ui.fragments.detail.DetailFragment

import dagger.Component


@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(basicSearchFragment: ListArticlesFragment)

    fun inject(detailFragment: DetailFragment)
}