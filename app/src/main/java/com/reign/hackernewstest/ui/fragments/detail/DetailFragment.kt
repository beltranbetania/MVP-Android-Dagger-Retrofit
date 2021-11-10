package com.reign.hackernewstest.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.reign.hackernewstest.R
import com.reign.hackernewstest.di.component.DaggerFragmentComponent
import com.reign.hackernewstest.di.module.FragmentModule
import com.reign.hackernewstest.ui.base.BaseFragment
import javax.inject.Inject


class DetailFragment : BaseFragment() , DetailContract.View  {
    var myWebView: WebView?=null
    var url:String?=null
    var lottieFile:LottieAnimationView?=null

    @Inject
    lateinit var presenter: DetailContract.Presenter

    private lateinit var rootView: View

    fun newInstance(url: String): DetailFragment {
        var frag: DetailFragment= DetailFragment()
        var args = Bundle()
        args.putString("URL", url)
        frag.setArguments(args)
        return frag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            url = arguments?.getString("URL")
        }
        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_detail, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun injectDependency() {
        val aboutComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        aboutComponent.inject(this)
    }
    override fun init() {
        myWebView = rootView?.findViewById(R.id.webview)
        lottieFile= rootView?.findViewById(R.id.lottie_main)
        myWebView!!.getSettings().setJavaScriptEnabled(true)
        myWebView!!.getSettings().setDomStorageEnabled(true)
        myWebView!!.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY)

        myWebView!!.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                lottieFile?.visibility=LinearLayout.INVISIBLE
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                lottieFile?.visibility=LinearLayout.INVISIBLE
                Toast.makeText(
                    activity,
                    activity!!.resources.getString(R.string.error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

    private fun initView() {
        lottieFile?.visibility=LinearLayout.VISIBLE
        myWebView?.loadUrl(url!!)

    }

    companion object {
        val TAG: String = "DetailFragment"
    }

    override fun onResume() {
        super.onResume()


    }



}