package com.devknightzzz.newsup.ui.sources.selection

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devknightzzz.newsup.R
import com.devknightzzz.newsup.base.NewsUpFragment
import com.devknightzzz.newsup.database.entity.Source
import com.devknightzzz.newsup.ui.sources.SourceAdapter
import kotlinx.android.synthetic.main.fragment_source_selection.*

/**
 * @author vinayagasundar
 */
class SourceSelectionFragment : NewsUpFragment() {

    private var sourceViewModel: SourceSelectionViewModel? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_source_selection, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sourceViewModel = ViewModelProviders.of(this)[SourceSelectionViewModel::class.java]

        progress_bar.visibility = View.VISIBLE
        sourceViewModel?.getAllSources()?.observe(this, Observer {
            it?.let {
                if (it.status) {
                    displayInRecyclerView(it.data)
                }
            }
        })
    }


    private fun displayInRecyclerView(sourceList: List<Source>?) {
        progress_bar.visibility = View.GONE

        if (sourceList == null) {
            return
        }

        val adapter = SourceAdapter(this.activity)
        adapter.add(sourceList)

        source_recycler_view.adapter = adapter
        source_recycler_view.layoutManager = GridLayoutManager(this.activity, 2)
    }
}