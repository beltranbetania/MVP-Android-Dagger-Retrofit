package com.reign.hackernewstest.ui.fragments.articlesList

import android.util.Log
import com.reign.hackernewstest.BaseApp
import com.reign.hackernewstest.api.ApiServiceInterface
import com.reign.hackernewstest.data.networkData.ArticleResponse
import com.reign.hackernewstest.data.realmData.RealmController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import android.graphics.ColorSpace.Model
import android.widget.Toast
import com.reign.hackernewstest.R
import com.reign.hackernewstest.data.realmData.ArticleLocal
import java.text.FieldPosition


class ListArticlesPresenter
: ListArticlesContract.Presenter {
    
    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: ListArticlesContract.View
    
    override fun getData() {
        var subscribe = api.getArticles("mobile").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: ArticleResponse? ->
                view.showProgress(false)
                var realResults= RealmController.with(BaseApp.instance)!!.saveArticles(list!!.hits)
               view.showList(realResults)

            }, { error ->
                view.showProgress(false)
                view.showErrorMsg(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun delete(articleLocal: ArticleLocal, position: Int) {
      var flag=RealmController.with(BaseApp.instance)!!.update(articleLocal!!, true)
      if (flag){
          view.showSnack(articleLocal, position)
      }else{
          view.showErrorMsg(BaseApp.instance.getString(R.string.error))
      }

    }

    override fun undo(articleLocal: ArticleLocal, position: Int) {
        var flag=RealmController.with(BaseApp.instance)!!.update(articleLocal!!, true)
        if (flag){
            view.undo(articleLocal, position)
        }else{
            view.showErrorMsg(BaseApp.instance.getString(R.string.error))
        }
    }

    override fun openURL(url: String?) {
        if  (url!=null){
            view.openURL(url)
        }else{
            view.showDialog(BaseApp.instance.getString(R.string.url_title))
        }
    }


    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ListArticlesContract.View) {
        this.view = view
        view.init()
    }
}