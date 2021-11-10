package com.reign.hackernewstest.data.networkData

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Article {
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("num_comments")
    @Expose
    var numComments: Any? = null

    @SerializedName("story_id")
    @Expose
    var storyId: Int? = null

    @SerializedName("story_title")
    @Expose
    var storyTitle: String? = null

    @SerializedName("story_url")
    @Expose
    var storyUrl: String? = null

    @SerializedName("created_at_i")
    @Expose
    var createdAtI: Int? = null

    @SerializedName("objectID")
    @Expose
    var objectID: String? = null
}