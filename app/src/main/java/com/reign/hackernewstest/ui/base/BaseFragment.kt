package com.reign.hackernewstest.ui.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment (){

    val ARGS_INSTANCE = "com.reign.hackernewstest"


    internal var mFragmentNavigation: FragmentNavigation?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
            mFragmentNavigation = context
        }
    }

    interface FragmentNavigation {
        fun pushFragment(fragment: Fragment)
    }

}