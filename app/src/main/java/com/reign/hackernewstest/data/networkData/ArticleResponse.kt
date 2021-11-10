package com.reign.hackernewstest.data.networkData

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class ArticleResponse {
    @SerializedName("hits")
    @Expose
    var hits: List<Article>? = null
}