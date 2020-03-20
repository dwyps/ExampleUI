package com.frangrgec.exampleui.ui.recyclerView

import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.frangrgec.exampleui.R
import kotlinx.android.synthetic.main.goggles_video_card.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<GogglesVideo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.goggles_video_card, parent, false)
        )
    }

    fun submitList(videoList: List<GogglesVideo>) {
        items = videoList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is VideoViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class VideoViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.video_title
        val duration = itemView.video_duration
        val thumbnail = itemView.video_thumbnail

        fun bind(video: GogglesVideo) {

            title.text = video.title
            duration.text = video.duration

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(video.thumbnail)
                .into(thumbnail)
        }
    }
}