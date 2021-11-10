package com.reign.hackernewstest.ui.fragments.articlesList

import com.reign.hackernewstest.data.realmData.ArticleLocal
import com.reign.hackernewstest.ui.base.BaseContract


class ListArticlesContract {
    interface View: BaseContract.View {
        fun init()
        fun showProgress(flag: Boolean)
        fun showErrorMsg(msg:String)
        fun showDialog(msg:String)
        fun showList(items:ArrayList<ArticleLocal>)
        fun undo (articleLocal: ArticleLocal, position: Int)
        fun showSnack (articleLocal: ArticleLocal, position: Int)
        fun openURL(url:String)
    }

    interface Presenter: BaseContract.Presenter<ListArticlesContract.View> {
        fun getData()
        fun delete (articleLocal: ArticleLocal, position: Int)
        fun undo(articleLocal: ArticleLocal, position: Int)
        fun openURL(url:String?)
    }
}