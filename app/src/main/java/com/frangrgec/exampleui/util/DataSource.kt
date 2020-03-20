package com.frangrgec.exampleui.util

import com.frangrgec.exampleui.ui.recyclerView.GogglesVideo

class DataSource {

    companion object {

        fun createDataSet(): ArrayList<GogglesVideo> {
            val list = ArrayList<GogglesVideo>()

            list.add(
                GogglesVideo(
                    "1m 23s",
                    "VID_001",
                    "https://live.staticflickr.com/65535/48485912537_226b7f93c6_b.jpg"
                )
            )
            list.add(
                GogglesVideo(
                    "4m 12s",
                    "VID_003",
                    "https://www.slrlounge.com/wp-content/uploads/2017/09/SLRLHowIShot-1.jpg"
                )
            )
            list.add(
                GogglesVideo(
                    "0m 54s",
                    "VID_002",
                    "https://s3.envato.com/files/259250513/DJI_0789_2266_C1.jpg"
                )
            )

            list.add(
                GogglesVideo(
                    "1m 23s",
                    "VID_001",
                    "https://sproutvideo.com/blog/wp-content/uploads/2016/10/boat1.jpeg"
                )
            )
            list.add(
                GogglesVideo(
                    "4m 12s",
                    "VID_003",
                    "https://i.redd.it/tv1jq9llwup11.jpg"
                )
            )
            list.add(
                GogglesVideo(
                    "0m 54s",
                    "VID_002",
                    "https://static.dezeen.com/uploads/2019/03/marton-mogyorosy-drone-photography-ricardo-bofill_dezeen_1704_col_2-852x1066.jpg"
                )
            )

            return list
        }
    }
}