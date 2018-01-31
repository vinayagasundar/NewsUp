package com.devknightzzz.newsup.ui.sources.selection

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devknightzzz.newsup.R
import com.devknightzzz.newsup.Status
import com.devknightzzz.newsup.base.NewsUpFragment
import com.devknightzzz.newsup.database.entity.Source
import com.devknightzzz.newsup.ui.landing.LandingActivity
import kotlinx.android.synthetic.main.fragment_source_selection.*

/**
 * @author vinayagasundar
 */
class SourceSelectionFragment : NewsUpFragment() {

    private lateinit var sourceViewModel: SourceSelectionViewModel

    private val sourceSelectionAdapter: SourceSelectionAdapter by lazy {
        SourceSelectionAdapter(activity, object : SourceSelectionAdapter.Callback {
            override fun onSourceSelectionChanged(selectedSources: List<Source>) {
                subscribe_source_btn.background.level = if (selectedSources.isNotEmpty()) {
                    1
                } else {
                    0
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_source_selection, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sourceViewModel = ViewModelProviders.of(this)[SourceSelectionViewModel::class.java]

        progress_bar.visibility = View.VISIBLE
        sourceViewModel.getAllSources().observe(this, Observer {
            it?.let {
                if (it.status == Status.SUCCESS) {
                    displayInRecyclerView(it.data)
                }
            }
        })

        subscribe_source_btn.setOnClickListener {
            if (sourceSelectionAdapter.getSelectedSources().isNotEmpty()) {
                sourceViewModel.addSelectedSource(sourceSelectionAdapter.getSelectedSources())
                startActivity(Intent(activity, LandingActivity::class.java))
            }
        }
    }


    private fun displayInRecyclerView(sourceList: List<Source>?) {
        progress_bar.visibility = View.GONE

        if (sourceList == null) {
            return
        }

        sourceSelectionAdapter.add(sourceList)

        source_recycler_view.adapter = sourceSelectionAdapter
        source_recycler_view.layoutManager = GridLayoutManager(this.activity, 2)
    }
}