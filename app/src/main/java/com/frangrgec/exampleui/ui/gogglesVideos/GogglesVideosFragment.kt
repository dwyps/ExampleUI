package com.frangrgec.exampleui.ui.gogglesVideos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.frangrgec.exampleui.R
import com.frangrgec.exampleui.ui.recyclerView.RecyclerViewAdapter
import com.frangrgec.exampleui.util.DataSource
import kotlinx.android.synthetic.main.fragment_goggles_videos.*

class GogglesVideosFragment : Fragment() {

    private lateinit var gogglesVideosViewModel: GogglesVideosViewModel
    private lateinit var videoAdapter: RecyclerViewAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        gogglesVideosViewModel =
                ViewModelProviders.of(this).get(GogglesVideosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_goggles_videos, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet() {
        val data = DataSource.createDataSet()
        videoAdapter.submitList(data)
    }

    private fun initRecyclerView() {

        videoAdapter = RecyclerViewAdapter()

        goggles_videos_recyclerview.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = videoAdapter
        }
    }
}
