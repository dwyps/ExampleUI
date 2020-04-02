package com.frangrgec.exampleui.ui.recyclerView

import android.app.Activity
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.frangrgec.exampleui.R
import kotlinx.android.synthetic.main.phone_videos_card.view.*
import kotlin.math.ceil

class PhoneViewAdapter(
    private val activity: Activity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<GogglesVideo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhoneVideoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.phone_videos_card, parent, false),
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
            is PhoneVideoViewHolder -> {

                holder.bind(items[position])

                holder.thumbnail.setOnClickListener {

                    var toPosition = position - 1

                    Log.e("prvi", items[position].toString() + position)
                    Log.e("drugi", items[position + 1].toString() + (toPosition))



                    items[position].expanded = (!items[position].expanded)

                    if (position % 2 != 0) {
                        Log.e("prvi", items[position].toString() + position)
                        Log.e("drugi", items[position - 1].toString() + (position - 1))

                        if (!items[position].expanded) {
                            toPosition = position + 1
                        }


                        val item = items[position]
                        items.removeAt(position)
                        items.add(toPosition, item)

//                            items[position] =
//                                items[position - 1].also { items[position - 1] = items[position] }

                        Log.e("prvi", items[position].toString() + position)
                        Log.e("drugi", items[position - 1].toString() + (toPosition))
                    }


                    notifyItemMoved(position, toPosition)
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PhoneVideoViewHolder constructor(
        itemView: View,
        private val activity: Activity
    ) : RecyclerView.ViewHolder(itemView) {

        val cardView = itemView.phone_card_view
        val title = itemView.phone_video_title
        val location = itemView.phone_video_location
        val duration = itemView.phone_video_duration
        val size = itemView.phone_video_size
        val thumbnail = itemView.phone_video_thumbnail
        val gradient = itemView.phone_video_gradient
        val dropdown = itemView.phone_dropdown_menu
        val delete = itemView.phone_video_delete_btn
        val download = itemView.phone_video_download_btn

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