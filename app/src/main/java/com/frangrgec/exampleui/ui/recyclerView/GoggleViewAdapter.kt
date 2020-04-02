package com.frangrgec.exampleui.ui.recyclerView

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.frangrgec.exampleui.R
import kotlinx.android.synthetic.main.goggles_video_card.view.*
import kotlin.math.ceil

class GoggleViewAdapter(
    private val activity: Activity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<GogglesVideo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GoggleVideoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.goggles_video_card, parent, false),
            activity
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].expanded)
            0
        else
            1
    }


    fun submitList(videoList: MutableList<GogglesVideo>) {
        items = videoList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GoggleVideoViewHolder -> {

                if (!items[position].expanded && position % 2 != 0) {
                    items[position + 1] =
                        items[position].also { items[position] = items[position + 1] }
                }

                holder.bind(items[position])

                holder.thumbnail.setOnClickListener {
                    items[position].expanded = (!items[position].expanded).also {

                        if (position % 2 != 0) {
                            items[position] =
                                items[position - 1].also { items[position - 1] = items[position] }
                        }
                    }



                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class GoggleVideoViewHolder constructor(
        itemView: View,
        private val activity: Activity
    ) : RecyclerView.ViewHolder(itemView) {

        val cardView = itemView.card_view
        val title = itemView.video_title
        val location = itemView.video_location
        val duration = itemView.video_duration
        val size = itemView.video_size
        val thumbnail = itemView.video_thumbnail
        val gradient = itemView.video_gradient
        val dropdown = itemView.dropdown_menu
        val delete = itemView.video_delete_btn
        val download = itemView.video_download_btn

        private fun getDP(px: Int): Int {

            val metrics = DisplayMetrics()

            activity.windowManager.defaultDisplay.getMetrics(metrics)

            return ceil((px * metrics.density).toDouble()).toInt()
        }

        fun bind(video: GogglesVideo) {

            dropdown.isClickable = true
            thumbnail.isClickable = true

            title.text = video.title
            duration.text = video.duration
            location.text = video.location
            size.text = video.size

            if (video.expanded) {

                gradient.visibility = View.VISIBLE
                delete.visibility = View.VISIBLE
                download.visibility = View.VISIBLE
                size.visibility = View.VISIBLE
                location.visibility = View.VISIBLE
                dropdown.rotation = 180F
                cardView.layoutParams.height = getDP(350)

            } else {

                dropdown.rotation = 360F
                size.visibility = View.GONE
                location.visibility = View.GONE
                delete.visibility = View.INVISIBLE
                download.visibility = View.INVISIBLE
                gradient.visibility = View.INVISIBLE
                cardView.layoutParams.height = getDP(200)
            }



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