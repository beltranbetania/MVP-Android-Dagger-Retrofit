package com.reign.hackernewstest.ui.adapters

import android.content.Context
import android.text.format.DateUtils
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.reign.hackernewstest.R
import android.widget.Toast
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.reign.hackernewstest.data.realmData.ArticleLocal
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class RecyclerViewAdapter(var context: Context, var data: ArrayList<ArticleLocal>,fragment: Fragment) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(v)
    }

    private val listener: RecyclerViewAdapter.onItemClickListener

    init {
        this.listener = fragment as RecyclerViewAdapter.onItemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemNameTV!!.text = if ( data[position].storyTitle!=null) data[position].storyTitle else  "-"
        holder.authorTV!!.text = data[position].author
        try {
            val date: Date
            val datepost = data[position].createdAt //"2016-10-17T16:02:42+00:00";
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            date = sdf.parse(datepost)
            val dateInLong: Long = date.getTime()
            val aux = DateUtils.getRelativeTimeSpanString(dateInLong)
            val timeEllapsed: String = aux.toString()
            holder.authorTV!!.text = data[position].author + " - "+timeEllapsed
        } catch (e: Exception) {

        }

if (position==data.size-1){
    holder.lineV.visibility=View.INVISIBLE
}else{
    holder.lineV.visibility=View.VISIBLE
}
        holder.itemContainer!!.setOnClickListener {
            val item = data[position]
          listener.itemClick(item.storyUrl)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: ArticleLocal, position: Int) {
        data.add(position, item)
        notifyItemInserted(position)
    }

    // Clean all elements of the recycler
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(list: List<ArticleLocal>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(var container: View) : RecyclerView.ViewHolder(
        container
    ) {
        var itemNameTV: TextView
        var itemContainer: LinearLayout
        var authorTV: TextView
        var lineV: View

        init {
            itemNameTV = container.findViewById(R.id.itemNameTV)
            itemContainer = container.findViewById(R.id.itemContainer)
            authorTV = container.findViewById(R.id.authorTV)
            lineV = container.findViewById(R.id.lineV)
        }
    }

    interface onItemClickListener {
        fun itemClick(url: String?)

    }
}