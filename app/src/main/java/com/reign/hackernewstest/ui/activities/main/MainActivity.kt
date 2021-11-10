package com.reign.hackernewstest.ui.activities.main


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.reign.hackernewstest.R
import com.reign.hackernewstest.di.component.DaggerActivityComponent

import com.reign.hackernewstest.di.module.ActivityModule


import javax.inject.Inject

import android.content.Context
import androidx.fragment.app.Fragment
import com.reign.hackernewstest.ui.fragments.articlesList.ListArticlesFragment
import com.reign.hackernewstest.ui.fragments.detail.DetailFragment
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


class MainActivity : AppCompatActivity(), MainContract.View  {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

        presenter.attach(this)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)
    }

    override fun init() {
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/FiraSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container1, ListArticlesFragment().newInstance(), ListArticlesFragment.TAG)
            .commit()
    }

    fun openDetailFragment (url:String){
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container1, DetailFragment().newInstance(url), DetailFragment.TAG)
            .commit()

    }
}