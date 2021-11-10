package com.reign.hackernewstest.data.realmData

import android.app.Application
import com.reign.hackernewstest.data.realmData.RealmController
import android.app.Activity
import androidx.fragment.app.Fragment
import com.reign.hackernewstest.data.networkData.Article
import io.realm.Realm
import io.realm.RealmResults
import java.lang.Exception

class RealmController(application: Application?) {
    val realm: Realm

    //Refresh the realm istance
    fun refresh() {
        realm.refresh()
    }

    companion object {
        var instance: RealmController? = null
            private set

        fun with(application: Application?): RealmController? {
            if (instance == null) {
                instance = RealmController(application)
            }
            return instance
        }


    }

    fun update( articleLocal: ArticleLocal, flag:Boolean):Boolean {
        var articleLocal: ArticleLocal? = realm.where(ArticleLocal::class.java)
            .equalTo("objectID", articleLocal!!.objectID).findFirst()
        try {
            realm.beginTransaction()
        } catch (e: Exception) {
        }

        if (articleLocal != null) {

            articleLocal.isDeleted=flag
            realm.insertOrUpdate(articleLocal) // using insert API
            realm.commitTransaction()
        }else{
            return false
        }

        return true
    }

    fun saveArticles( list : List<Article>?): ArrayList<ArticleLocal> {
        val list: ArrayList<ArticleLocal> = ArrayList()
        var results: RealmResults<ArticleLocal> = realm
            .where(ArticleLocal::class.java)
            .findAll()
            try {
                realm.beginTransaction()
            } catch (e: Exception) {
            }

        for (articleRemote in list!!) {
            var selectedArticle: List<ArticleLocal> = results.filter { s -> s.objectID == articleRemote.objectID }

                if (selectedArticle.size==0) {
                    val articleLocal = ArticleLocal()
                    articleLocal.author=articleRemote.author
                    articleLocal.objectID=articleRemote.objectID
                    articleLocal.createdAt=articleRemote.createdAt
                    articleLocal.title=articleRemote.title
                    articleLocal.url=articleRemote.url
                    articleLocal.author=articleRemote.author
                    articleLocal.storyId=articleRemote.storyId
                    articleLocal.storyTitle=articleRemote.storyTitle
                    articleLocal.storyUrl=articleRemote.storyUrl
                    articleLocal.createdAtI=articleRemote.createdAtI
                    articleLocal.isDeleted  =false
                    realm.insertOrUpdate(articleLocal)

                }


        }
        realm.commitTransaction()
        results = realm
        .where(ArticleLocal::class.java)
            .equalTo("isDeleted", false)
            .findAll()

        list.addAll(realm.copyFromRealm(results));

        return list
    }

    init {
        realm = Realm.getDefaultInstance()
    }
}