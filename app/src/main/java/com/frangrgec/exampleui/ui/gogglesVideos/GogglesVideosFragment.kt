package com.frangrgec.exampleui.ui.gogglesVideos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.frangrgec.exampleui.R

class GogglesVideosFragment : Fragment() {

    private lateinit var gogglesVideosViewModel: GogglesVideosViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        gogglesVideosViewModel =
                ViewModelProviders.of(this).get(GogglesVideosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_goggles_videos, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        gogglesVideosViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
