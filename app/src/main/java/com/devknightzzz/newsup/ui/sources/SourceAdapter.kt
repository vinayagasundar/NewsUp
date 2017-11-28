package com.devknightzzz.newsup.ui.sources

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.devknightzzz.newsup.R
import com.devknightzzz.newsup.database.entity.Source

/**
 * @author vinayagasundar
 */
class SourceAdapter(private val context: Context,
                    private var callback: Callback? = null)
    : RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {

    private val data: MutableList<Source> = mutableListOf()
    private val selectedData: MutableList<Source> = mutableListOf()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SourceViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_source, parent, false)
        return SourceViewHolder(view)
    }

    override fun onBindViewHolder(holder: SourceViewHolder?, position: Int) {
        val source: Source = data[position]

        if (selectedData.contains(source)) {
            holder?.bind(source, 1)
        } else {
            holder?.bind(source)
        }

        holder?.itemView?.setOnClickListener {
            if (selectedData.contains(source)) {
                selectedData.remove(source)
            } else {
                selectedData.add(source)
            }

            callback?.onSourceSelectionChanged(selectedData)

            notifyItemChanged(holder.adapterPosition)
        }
    }

    fun add(data: List<Source>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun getSelectedSources(): MutableList<Source> {
        return selectedData
    }

    class SourceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var logoImage: ImageView = view.findViewById(R.id.logo_image)
        private var nameText: TextView = view.findViewById(R.id.name)
        private var selectionView: View = view.findViewById(R.id.selection_view)

        fun bind(source: Source, level: Int = 0) {
            nameText.text = source.name

            if (level == 0) {
                selectionView.visibility = View.GONE
            } else {
                selectionView.visibility = View.VISIBLE
            }

            Glide.with(logoImage.context)
                    .load("https://icons.better-idea.org/icon?url=${source.url}&size=100..140..160")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(logoImage)
        }
    }

    interface Callback {
        fun onSourceSelectionChanged(selectedSources: List<Source>)
    }
}