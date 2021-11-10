package com.reign.hackernewstest.ui.fragments.articlesList

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.reign.hackernewstest.R
import com.reign.hackernewstest.di.component.DaggerFragmentComponent
import com.reign.hackernewstest.di.module.FragmentModule
import com.reign.hackernewstest.ui.activities.main.MainActivity
import com.reign.hackernewstest.ui.adapters.RecyclerViewAdapter
import com.reign.hackernewstest.ui.base.BaseFragment
import com.reign.hackernewstest.ui.customUIComponents.SwipeToDeleteCallback
import javax.inject.Inject
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.reign.hackernewstest.data.realmData.ArticleLocal


class ListArticlesFragment : BaseFragment() , ListArticlesContract.View, RecyclerViewAdapter.onItemClickListener {
    var itemsContainerRV: RecyclerView? = null
    var itemAdapter: RecyclerViewAdapter? = null
    var swipeContainer: SwipeRefreshLayout? = null
    var data: ArrayList<ArticleLocal> = ArrayList<ArticleLocal>()
    @Inject
    lateinit var presenter: ListArticlesContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): ListArticlesFragment {
        var frag: ListArticlesFragment= ListArticlesFragment()
        var args = Bundle()

        frag.setArguments(args)

        return frag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {


        }
        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_list_articles, container, false)



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

    }

    override fun showProgress(flag: Boolean) {
        swipeContainer!!.isRefreshing=false
    }

    override fun showErrorMsg(msg: String) {
        Toast.makeText(activity, R.string.error, Toast.LENGTH_SHORT).show()

    }

    override fun showDialog(msg: String) {
        val dialog = AlertDialog.Builder(requireActivity())
            .setTitle("")
            .setMessage(msg)
            .setPositiveButton(R.string.ok) { view, _ ->
                view.dismiss()
            }
            .setCancelable(false)
            .create()

        dialog.show()
    }

    override fun showList(items: ArrayList<ArticleLocal>) {
        data.clear()
        data.addAll(items)
        itemAdapter!!.notifyDataSetChanged()
    }

    override fun undo(articleLocal: ArticleLocal, position: Int) {
        itemAdapter!!.restoreItem(articleLocal, position)
        itemsContainerRV!!.scrollToPosition(position)
    }

    override fun showSnack(articleLocal: ArticleLocal, position: Int) {
        itemAdapter!!.removeItem(position)
        val snackbar = Snackbar.make(
            itemsContainerRV!!,
            R.string.item_removed,
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction(R.string.undo) {
            presenter.undo(articleLocal, position)

        }
        snackbar.setActionTextColor(Color.YELLOW)
        snackbar.show()
    }

    override fun openURL(url: String) {
        (activity as MainActivity?)!!.openDetailFragment(url)
    }

    private fun initView() {
        swipeContainer = rootView?.findViewById(R.id.swipeContainer);
        itemsContainerRV = rootView?.findViewById(R.id.itemsContainerRV)
        itemAdapter = activity?.applicationContext?.let { RecyclerViewAdapter(it, data, this) }
        itemAdapter!!.notifyDataSetChanged()
        itemsContainerRV!!.setAdapter(itemAdapter)
        val itemTouchhelper = ItemTouchHelper(object : SwipeToDeleteCallback(activity?.applicationContext) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                val item = itemAdapter!!.data[position]
                presenter.delete(item, position)
            }
        })
        itemTouchhelper.attachToRecyclerView(itemsContainerRV)

        swipeContainer!!.setOnRefreshListener {
            fetchTimelineAsync(0)
        }

        // Configure the refreshing colors
        swipeContainer!!.setColorSchemeResources(android.R.color.holo_orange_dark,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light,
            android.R.color.holo_orange_light);

        swipeContainer!!.isRefreshing=true
        presenter.getData()
    }

    fun fetchTimelineAsync(page: Int) {
        presenter.getData()
    }

    companion object {
        val TAG: String = "ListArticlesFragment"
    }

    override fun onResume() {
        super.onResume()


    }

    override fun itemClick(url: String?) {
        presenter.openURL(url)
    }


}