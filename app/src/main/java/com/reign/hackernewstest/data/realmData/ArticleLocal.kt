package com.reign.hackernewstest.data.realmData

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class ArticleLocal: RealmObject() {
    @PrimaryKey
    var objectID: String? = null
    var createdAt: String? = null
    var title: String? = null
    var url: String? = null
    var author: String? = null
    var storyId: Int? = null
    var storyTitle: String? = null
    var storyUrl: String? = null
    var createdAtI: Int? = null
    var isDeleted: Boolean? = null
}